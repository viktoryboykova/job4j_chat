package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.job4j.validation.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RoomDTO {

    @Min(value = 1, message = "Id must be more than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @NotBlank(message = "Name of room must be not empty")
    private String name;

    @NotBlank(message = "Name of owner must be not empty")
    private String owner;

    public RoomDTO(Room room) {
        this.name = room.getName();
        this.owner = room.getPerson().getUsername();
        this.id = room.getId();
    }

    public RoomDTO() {
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
