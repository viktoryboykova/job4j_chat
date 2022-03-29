package ru.job4j.domain;

public class PersonDTO {

    private String username;

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
