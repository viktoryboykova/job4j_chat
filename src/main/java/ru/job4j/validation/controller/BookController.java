package ru.job4j.validation.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.validation.Operation;
import ru.job4j.validation.model.Book;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/book")
public class BookController {

    private final Map<Integer, Book> repository = new HashMap<>();
    private int id = 1;

    @PostMapping
    @Validated(Operation.OnCreate.class)
    public Book save(@Valid @RequestBody Book book) {
        book.setId(id++);
        repository.put(id, book);
        return book;
    }
}
