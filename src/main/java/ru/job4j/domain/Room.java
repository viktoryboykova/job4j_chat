package ru.job4j.domain;

import ru.job4j.validation.Operation;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Min(value = 1, message = "Id must be more than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    private int id;

    @NotBlank(message = "Name of room must be not empty")
    private String name;

    private Date created = new Date();

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;


    @OneToMany(orphanRemoval = true, mappedBy = "room")
    private List<Message> messages = new ArrayList<>();

    public Room(String name, Person person) {
        this.name = name;
        this.created = new Date(System.currentTimeMillis());
        this.person = person;
    }
    public Room() {
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
