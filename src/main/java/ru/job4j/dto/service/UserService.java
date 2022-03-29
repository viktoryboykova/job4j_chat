package ru.job4j.dto.service;

import org.springframework.stereotype.Service;
import ru.job4j.dto.model.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    private Map<Integer, User> users = new HashMap<>();

    public User save(User user) {
        users.put(users.size() + 1, user);
        return user;
    }
}
