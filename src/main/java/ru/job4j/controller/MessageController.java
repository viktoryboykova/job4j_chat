package ru.job4j.controller;

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
    public Message findById(@PathVariable int id) {
        return chatService.findMessageById(id);
    }

    @PostMapping("/")
    public Message create(@RequestBody Message message) {
        if (message.getName() == null) {
            throw new NullPointerException("Name of message mustn't be empty");
        }
        return chatService.saveMessage(message);
    }

    @PutMapping("/")
    public void update(@RequestBody Message message) {
        chatService.updateMessage(message);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        chatService.deleteMessage(id);
    }
}
