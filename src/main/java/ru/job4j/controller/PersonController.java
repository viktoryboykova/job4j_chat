package ru.job4j.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.domain.PersonDTO;
import ru.job4j.domain.Role;
import ru.job4j.service.ChatService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {
    private final ChatService chatService;

    private final BCryptPasswordEncoder encoder;

    private final ObjectMapper objectMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class.getSimpleName());

    public PersonController(ChatService chatService, BCryptPasswordEncoder encoder, ObjectMapper objectMapper) {
        this.chatService = chatService;
        this.encoder = encoder;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/all")
    public List<PersonDTO> findAll() {
        List<Person> persons = chatService.findAllPersons();
        List<PersonDTO> personsDTO = new ArrayList<>();
        persons.forEach(p -> personsDTO.add(new PersonDTO(p)));
        return personsDTO;
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable int id) {
        return chatService.findPersonById(id);
    }

    @GetMapping("/{id}/roles")
    public Role getRoleOfCurrentUser(@PathVariable int id) {
        Person currentPerson = chatService.findPersonById(id);
        return currentPerson.getRole();
    }

    @PostMapping("/sign-up")
    public Person signUp(@RequestBody Person person) {
        String username = person.getUsername();
        String password = person.getPassword();
        if (chatService.findPersonByUsername(username) != null) {
            throw new IllegalArgumentException("User with this username already exists");
        }
        if (username == null || password == null) {
            throw new NullPointerException("Username and password mustn't be empty");
        }
        person.setPassword(encoder.encode(person.getPassword()));
        person.setRole(chatService.findRoleByName("USER"));
        return chatService.save(person);
    }



    @ExceptionHandler(value = { IllegalArgumentException.class })
    public void exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", e.getMessage());
            put("type", e.getClass());
        }}));
        LOGGER.error(e.getLocalizedMessage());
    }
}
