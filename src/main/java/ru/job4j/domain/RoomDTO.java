package ru.job4j.domain;

public class RoomDTO {

    private String name;

    private String owner;

    public RoomDTO(Room room) {
        this.name = room.getName();
        this.owner = room.getPerson().getUsername();
    }

    public RoomDTO() {
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
