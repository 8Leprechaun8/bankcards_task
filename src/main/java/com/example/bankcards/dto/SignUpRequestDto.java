package com.example.bankcards.dto;

import com.example.bankcards.entity.Role;

import java.util.UUID;

public class SignUpRequestDto {

    private String firstName;

    private String secondName;

    private String lastName;

    private String username;

    private String password;

    private String email;

    public SignUpRequestDto() {
    }

    public SignUpRequestDto(String firstName,
                            String secondName,
                            String lastName,
                            String login,
                            String password,
                            String email) {

        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.username = login;
        this.password = password;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
