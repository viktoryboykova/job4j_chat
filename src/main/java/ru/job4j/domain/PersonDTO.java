package ru.job4j.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.job4j.validation.Operation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class PersonDTO {

    @Min(value = 1, message = "Id must be more than 0", groups = {
            Operation.OnUpdate.class, Operation.OnDelete.class
    })
    @JsonIgnore
    private int id;

    @NotBlank(message = "Username must be not empty")
    private String username;

    @NotBlank(message = "Name of role must be not empty")
    private String role;

    public PersonDTO(Person person) {
        this.username = person.getUsername();
        this.role = person.getRole().getRole();
    }

     public PersonDTO() {

     }



    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
