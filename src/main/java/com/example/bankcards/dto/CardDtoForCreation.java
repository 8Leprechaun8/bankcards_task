package com.example.bankcards.dto;

import com.example.bankcards.entity.CardStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class CardDtoForCreation {

    private String number;
    private LocalDateTime expiration;
    private Double balance;
    private CardStatus status;
    private UUID userId;
    private Boolean isArchived;

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
