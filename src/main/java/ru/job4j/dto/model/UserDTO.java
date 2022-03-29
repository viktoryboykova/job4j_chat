package ru.job4j.dto.model;

public class UserDTO {

    private String name;

    private String surname;

    private int addressId;

    public UserDTO(String name, String surname, int addressId) {
        this.name = name;
        this.surname = surname;
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
