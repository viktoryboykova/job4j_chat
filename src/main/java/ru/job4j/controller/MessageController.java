package ru.job4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Message;
import ru.job4j.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final ChatService chatService;

    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public List<Message> findAll() {
        return chatService.findAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        return chatService.findMessageById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Message> create(@RequestBody Message message) {
        return chatService.saveMessage(message);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        return chatService.updateMessage(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return chatService.deleteMessage(id);
    }
}
