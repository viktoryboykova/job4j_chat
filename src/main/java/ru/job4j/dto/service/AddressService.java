package ru.job4j.dto.service;

import org.springframework.stereotype.Service;
import ru.job4j.dto.model.Address;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {

    private Map<Integer, Address> addresses = new HashMap<>();

    public Optional<Address> findById(int id) {
        return Optional.ofNullable(addresses.get(id));
    }
}
