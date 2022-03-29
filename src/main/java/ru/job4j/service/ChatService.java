package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.domain.*;
import ru.job4j.repository.MessageRepository;
import ru.job4j.repository.PersonRepository;
import ru.job4j.repository.RoleRepository;
import ru.job4j.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChatService {
    private final RoomRepository roomRepository;
    private final MessageRepository messageRepository;
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public ChatService(RoomRepository roomRepository, MessageRepository messageRepository,
                       PersonRepository personRepository, RoleRepository roleRepository) {
        this.roomRepository = roomRepository;
        this.messageRepository = messageRepository;
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<Person> findAllPersons() {
        List<Person> rsl = new ArrayList<>();
        personRepository.findAll().forEach(rsl :: add);
        return rsl;
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findPersonById(int id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Person is not found. Please, check id."
                ));
    }

    public Person findPersonByUsername(String username) {
        return personRepository.findByUsername(username)
                .orElse(null);
    }

    public List<Role> findAllRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByRole(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role is not found. Please, check id."
                ));
    }

    public Role findRoleById(int id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Role is not found. Please, check id."
                ));
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void update(Role role) {
        roleRepository.save(role);
    }

    public void deleteRole(int id) {
        Role role = new Role();
        role.setId(id);
        roleRepository.delete(role);
    }

    public List<Message> findAllMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Message findMessageById(int id) {
        return messageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Message is not found. Please, check id."
                ));
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public void update(Message message) {
        messageRepository.save(message);
    }

    public void deleteMessage(int id) {
        Message message = new Message();
        message.setId(id);
        messageRepository.delete(message);
    }

    public List<Room> findAllRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Room findRoomById(int id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Room is not found. Please, check id."
                ));
    }

    public Room findRoomByName(String name) {
        return roomRepository.findRoomByName(name)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Room is not found. Please, check id."
                ));
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void update(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        roomRepository.delete(room);
    }


}
