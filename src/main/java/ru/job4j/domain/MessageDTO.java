package ru.job4j.domain;

public class MessageDTO {

    private String name;

    private String room;

    private String creator;

    public MessageDTO(Message message) {
        this.name = message.getName();
        this.room = message.getRoom().getName();
        this.creator = message.getPerson().getUsername();
    }

    public MessageDTO() {
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
