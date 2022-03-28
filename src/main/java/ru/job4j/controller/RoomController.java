package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.service.ChatService;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final ChatService chatService;

    public RoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Room>> findAll() {
        List<Room> rooms = chatService.findAllRooms();
        return new ResponseEntity<>(rooms,
        rooms.size() != 0? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> findById(@PathVariable int id) {
        Room room = chatService.findRoomById(id);
        return new ResponseEntity<>(room,
                room != null? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @PostMapping("/create")
    public ResponseEntity<Room> create(@RequestBody Room room) {
        if (room.getName() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        }
        room.setCreated(new Date(System.currentTimeMillis()));
        return new ResponseEntity<>(chatService.saveRoom(room),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        chatService.updateRoom(room);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        chatService.deleteRoom(id);
        return ResponseEntity.ok("Room was deleted");
    }
}
