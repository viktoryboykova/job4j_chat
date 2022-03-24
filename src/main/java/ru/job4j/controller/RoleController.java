package ru.job4j.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Role;
import ru.job4j.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final ChatService chatService;

    public RoleController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/")
    public List<Role> findAll() {
        return chatService.findAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> findById(@PathVariable int id) {
        return chatService.findRoleById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        return chatService.saveRole(role);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Role role) {
        return chatService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        return chatService.deleteRole(id);
    }
}
