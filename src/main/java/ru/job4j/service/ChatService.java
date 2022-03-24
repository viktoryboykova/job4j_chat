package ru.job4j.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Message;
import ru.job4j.domain.Person;
import ru.job4j.domain.Role;
import ru.job4j.domain.Room;
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

    public void savePerson(Person person) {
        personRepository.save(person);
    }

    public List<Role> findAllRoles() {
        return StreamSupport.stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByRole(name);
    }

    public ResponseEntity<Role> findRoleById(int id) {
        var role = this.roleRepository.findById(id);
        return new ResponseEntity<>(
                role.orElse(new Role()),
                role.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<Role> saveRole(Role role) {
        return new ResponseEntity<>(
                this.roleRepository.save(role),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> updateRole(Role role) {
        this.roleRepository.save(role);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteRole(int id) {
        Role role = new Role();
        role.setId(id);
        this.roleRepository.delete(role);
        return ResponseEntity.ok().build();
    }

    public List<Message> findAllMessages() {
        return StreamSupport.stream(messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Message> findMessageById(int id) {
        var message = this.messageRepository.findById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<Message> saveMessage(Message message) {
        return new ResponseEntity<>(
                this.messageRepository.save(message),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> updateMessage(Message message) {
        this.messageRepository.save(message);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteMessage(int id) {
        Message message = new Message();
        message.setId(id);
        this.messageRepository.delete(message);
        return ResponseEntity.ok().build();
    }

    public List<Room> findAllRooms() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ResponseEntity<Room> findRoomById(int id) {
        var room = this.roomRepository.findById(id);
        return new ResponseEntity<>(
                room.orElse(new Room()),
                room.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    public ResponseEntity<Room> saveRoom(Room room) {
        return new ResponseEntity<>(
                this.roomRepository.save(room),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Void> updateRoom(Room room) {
        this.roomRepository.save(room);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteRoom(int id) {
        Room room = new Room();
        room.setId(id);
        this.roomRepository.delete(room);
        return ResponseEntity.ok().build();
    }


}
