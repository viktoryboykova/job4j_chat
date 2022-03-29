package ru.job4j.validation.model;

import ru.job4j.validation.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Book {

    @NotNull(message = "Id must be non null", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private Integer id;

    @NotBlank(message = "Title must be not empty")
    private String title;

    @Min(value = 1, message = "Year must be more than 0")
    private int year;

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
