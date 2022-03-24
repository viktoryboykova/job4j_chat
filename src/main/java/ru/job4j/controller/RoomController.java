package ru.job4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {
    private final ChatService chatService;

    public RoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public List<Room> findAll() {
        return chatService.findAllRooms();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        return chatService.findRoomById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        return chatService.saveRoom(room);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        return chatService.updateRoom(room);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return chatService.deleteRoom(id);
    }
}
