package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Room;
import ru.job4j.domain.RoomDTO;
import ru.job4j.service.ChatService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final ChatService chatService;

    public RoomController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RoomDTO>> findAll() {
        List<Room> rooms = chatService.findAllRooms();
        List<RoomDTO> roomsDTO = new ArrayList<>();
        rooms.forEach(r -> roomsDTO.add(new RoomDTO(r)));
        return new ResponseEntity<>(roomsDTO,
        roomsDTO.size() != 0? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable int id) {
        Room room = chatService.findRoomById(id);
        RoomDTO roomDTO = new RoomDTO(room);
        return new ResponseEntity<>(roomDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO) {
        if (roomDTO.getName() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        }
        if (roomDTO.getOwner() == null) {
            throw new NullPointerException("Owner of room mustn't be empty");
        }
        Room room = new Room(roomDTO.getName(), chatService.findPersonByUsername(roomDTO.getOwner()));
        Room savedRoom = chatService.save(room);
        return new ResponseEntity<>(new RoomDTO(savedRoom),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestBody Room room) {
        Room roomFromDatabase = chatService.findRoomById(room.getId());
        if (room.getName() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        } else {
            roomFromDatabase.setName(room.getName());
        }
        chatService.update(roomFromDatabase);
        return ResponseEntity.ok("Room was updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        chatService.deleteRoom(id);
        return ResponseEntity.ok("Room was deleted");
    }
}
