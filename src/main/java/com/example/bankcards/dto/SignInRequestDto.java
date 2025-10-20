package com.example.bankcards.dto;

import jakarta.validation.constraints.NotNull;

public class SignInRequestDto {

    @NotNull(message = "Username is mandatory")
    private String username;

    @NotNull(message = "Password is mandatory")
    private String password;

    public SignInRequestDto() {
    }

    public SignInRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
