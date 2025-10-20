package com.example.bankcards.dto;

import jakarta.validation.constraints.NotNull;


public class SignUpRequestDto {

    @NotNull(message = "FirstName is mandatory")
    private String firstName;

    @NotNull(message = "SecondName is mandatory")
    private String secondName;

    @NotNull(message = "LastName is mandatory")
    private String lastName;

    @NotNull(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Password is mandatory")
    private String password;

    @NotNull(message = "Email is mandatory")
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
