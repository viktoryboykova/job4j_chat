package ru.job4j.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.service.ChatService;

import java.util.Date;
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
    public Room findById(@PathVariable int id) {
        return chatService.findRoomById(id);
    }

    @PostMapping("/create")
    public Room create(@RequestBody Room room) {
        if (room.getName() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        }
        room.setCreated(new Date(System.currentTimeMillis()));
        return chatService.saveRoom(room);
    }

    @PutMapping("/")
    public void update(@RequestBody Room room) {
        chatService.updateRoom(room);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        chatService.deleteRoom(id);
    }
}
