package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.job4j.validation.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class MessageDTO {

    @Min(value = 1, message = "Id must be more than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @NotBlank(message = "Name of message must be not empty")
    private String name;

    @NotBlank(message = "Name of room must be not empty")
    private String room;

    @NotBlank(message = "Username of creator must be not empty")
    private String creator;

    public MessageDTO(Message message) {
        this.name = message.getName();
        this.room = message.getRoom().getName();
        this.creator = message.getPerson().getUsername();
        this.id = message.getId();
    }

    public MessageDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
