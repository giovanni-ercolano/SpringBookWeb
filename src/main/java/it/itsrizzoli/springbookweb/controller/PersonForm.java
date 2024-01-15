package it.itsrizzoli.springbookweb.controller;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonForm {

    @NotNull
    @Size(min=2, max=30)
    String username;
    @NotNull
    @Size(min=6, max=20)
    String password;
    @NotNull
    @Size(min=2, max=30)
    String name;

    @NotNull
    @Size(min=2, max=30)
    String surname;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "PersonForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
