package ru.job4j.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Person;
import ru.job4j.domain.Role;
import ru.job4j.service.ChatService;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class PersonController {
    private final ChatService chatService;
    private final BCryptPasswordEncoder encoder;

    public PersonController(ChatService chatService, BCryptPasswordEncoder encoder) {
        this.chatService = chatService;
        this.encoder = encoder;
    }

    @GetMapping("/all")
    public List<Person> findAll() {
        return chatService.findAllPersons();
    }

    @GetMapping("/{id}/roles")
    public Role getRoleOfCurrentUser(@PathVariable int id) {
        Person currentPerson = chatService.findPersonById(id).getBody();
        return currentPerson.getRole();
    }

    @PostMapping("/sign-up")
    public Person signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        person.setRole(chatService.findRoleByName("USER"));
        return chatService.savePerson(person);
    }
}
