package com.example.bankcards.dto;

import com.example.bankcards.entity.CardStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;
import java.util.UUID;

public class CardDto {

    private UUID id;

    @NotNull(message = "Number is mandatory")
    @Pattern(regexp = "[0-9][0-9][0-9][0-9][ ][0-9][0-9][0-9][0-9][ ][0-9][0-9][0-9][0-9][ ][0-9][0-9][0-9][0-9]")
    private String number;

    @NotNull(message = "Expiration date is mandatory")
    private LocalDateTime expiration;

    @NotNull(message = "Balance is mandatory")
    private Double balance;

    @NotNull(message = "Status is mandatory")
    private CardStatus status;

    @NotNull(message = "UserId is mandatory")
    private UUID userId;

    @NotNull(message = "Archived-flag is mandatory")
    private Boolean isArchived;

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public Double getBalance() {
        return balance;
    }

    public CardStatus getStatus() {
        return status;
    }

    public UUID getUserId() {
        return userId;
    }

    public Boolean getArchived() {
        return isArchived;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setStatus(CardStatus status) {
        this.status = status;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }
}
