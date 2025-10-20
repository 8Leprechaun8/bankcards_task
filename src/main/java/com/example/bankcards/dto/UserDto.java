package com.example.bankcards.dto;

import com.example.bankcards.entity.Role;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public class UserDto {

    public UserDto() {
    }

    public UserDto(UUID id,
                   String firstName,
                   String secondName,
                   String lastName,
                   String login,
                   String password,
                   Role role,
                   String email,
                   Boolean isArchived,
                   List<CardDto> cardList) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.username = login;
        this.password = password;
        this.isArchived = isArchived;
        this.role = role;
        this.email = email;
        this.cardList = cardList;
    }

    @NotNull(message = "Id is mandatory")
    private UUID id;

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

    @NotNull(message = "Role is mandatory")
    private Role role;

    @NotNull(message = "Email is mandatory")
    private String email;

    @NotNull(message = "Archived-flag is mandatory")
    private Boolean isArchived;

    private List<CardDto> cardList;

    public UUID getId() {
        return id;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public List<CardDto> getCardList() {
        return cardList;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setArchived(Boolean archived) {
        this.isArchived = archived;
    }

    public void setCardList(List<CardDto> cardList) {
        this.cardList = cardList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
