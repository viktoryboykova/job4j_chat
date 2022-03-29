package ru.job4j.dto.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.dto.model.Address;
import ru.job4j.dto.model.User;
import ru.job4j.dto.model.UserDTO;
import ru.job4j.dto.service.UserService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@RestController
public class ExampleController {

    private final Map<Integer, Address> addressService = new HashMap<>(Map.ofEntries(
            Map.entry(1, new Address(
                    1, "Russia", "Moscow", "Gogolya", "5a"
            )),
            Map.entry(2, new Address(
                    2, "Russia", "St. Petersburg", "Dostoevskogo", "10"
            )),
            Map.entry(3, new Address(
                    3, "Russia", "Ufa", "Aksakova", "93"
            ))
    ));

    private UserService userService;

    public ExampleController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/example1")
    public User example1(@RequestBody UserDTO userDTO) {
        var address = addressService.get(userDTO.getAddressId());
        var user = new User(userDTO.getName(), userDTO.getSurname());
        user.setAddress(address);
        return userService.save(user);
    }

    @PatchMapping("/example2")
    public Address example2(@RequestBody Address address) throws InvocationTargetException, IllegalAccessException {
        var current = addressService.get(address.getId());
        if (current == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var methods = current.getClass().getDeclaredMethods();
        var namePerMethod = new HashMap<String, Method>();
        for (var method: methods) {
            var name = method.getName();
            if (name.startsWith("get") || name.startsWith("set")) {
                namePerMethod.put(name, method);
            }
        }
        for (var name : namePerMethod.keySet()) {
            if (name.startsWith("get")) {
                var getMethod = namePerMethod.get(name);
                var setMethod = namePerMethod.get(name.replace("get", "set"));
                if (setMethod == null) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid properties mapping");
                }
                var newValue = getMethod.invoke(address);
                if (newValue != null) {
                    setMethod.invoke(current, newValue);
                }
            }
        }
        addressService.put(address.getId(), address);
        return current;
    }

    @PostMapping("/example3")
    public Map<String, String> saveAddress(@RequestBody Map<String, String> body) {
        var address = new Address(
                0, body.get("country"), body.get("city"), body.get("street"), body.get("house")
        );
        address.setId(addressService.size() + 1);
        addressService.put(address.getId(), address);
        return Map.of(
                "id", String.valueOf(address.getId()),
                "country", address.getCountry(),
                "city", address.getCity(),
                "street", address.getStreet(),
                "house", address.getHouse()
        );
    }
}
