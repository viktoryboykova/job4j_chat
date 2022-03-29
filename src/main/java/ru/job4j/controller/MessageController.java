package ru.job4j.controller;

import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Message;
import ru.job4j.domain.MessageDTO;
import ru.job4j.service.ChatService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final ChatService chatService;

    public MessageController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public List<MessageDTO> findAll() {
        List<Message> messages = chatService.findAllMessages();
        List<MessageDTO> messagesDTO = new ArrayList<>();
        messages.forEach(r -> messagesDTO.add(new MessageDTO(r)));
        return messagesDTO;
    }

    @GetMapping("/{id}")
    public MessageDTO findById(@PathVariable int id) {
        Message message = chatService.findMessageById(id);
        return new MessageDTO(message);
    }

    @PostMapping("/")
    public MessageDTO create(@RequestBody MessageDTO messageDTO) {
        if (messageDTO.getName() == null) {
            throw new NullPointerException("Name of message mustn't be empty");
        }
        if (messageDTO.getRoom() == null) {
            throw new NullPointerException("Name of room mustn't be empty");
        }
        if (messageDTO.getCreator() == null) {
            throw new NullPointerException("Creator mustn't be empty");
        }
        Message message = new Message(messageDTO.getName(), chatService.findRoomByName(messageDTO.getRoom()), chatService.findPersonByUsername(messageDTO.getCreator()));
        Message savedMessage = chatService.save(message);
        return new MessageDTO(savedMessage);
    }

    @PutMapping("/")
    public void update(@RequestBody Message message) {
        Message messageFromDatabase = chatService.findMessageById(message.getId());
        if (message.getName() != null) {
            messageFromDatabase.setName(message.getName());
        } else {
            throw new NullPointerException("Name of message mustn't be empty");
        }
        chatService.update(messageFromDatabase);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        chatService.deleteMessage(id);
    }
}
