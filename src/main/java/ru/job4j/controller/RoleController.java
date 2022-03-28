package ru.job4j.controller;

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
    public Role findById(@PathVariable int id) {
        return chatService.findRoleById(id);
    }

    @PostMapping("/")
    public Role create(@RequestBody Role role) {
        if (role.getRole() == null) {
            throw new NullPointerException("Role mustn't be empty");
        }
        return chatService.saveRole(role);
    }

    @PutMapping("/")
    public void update(@RequestBody Role role) {
        chatService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        chatService.deleteRole(id);
    }
}
