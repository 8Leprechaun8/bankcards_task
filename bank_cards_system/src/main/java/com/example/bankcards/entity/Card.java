package com.example.bankcards.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "public", name = "cards")
public class Card {

    public Card() {
    }

    public Card(UUID id,
                String number,
                LocalDateTime expiration,
                Double balance,
                CardStatus status,
                User user,
                Boolean isArchived) {
        this.id = id;
        this.number = number;
        this.expiration = expiration;
        this.balance = balance;
        this.status = status;
        this.user =  user;
        this.isArchived = isArchived;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "number", length = 19, nullable = false)
    private String number;

    @Column(name = "expiration", nullable = false)
    private LocalDateTime expiration;

    @Column(name = "balance", nullable = false)
    private Double balance;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(name = "archive_flag", nullable = false)
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

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void setArchived(Boolean archived) {
        isArchived = archived;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(id, card.id) &&
                Objects.equals(number, card.number) &&
                Objects.equals(expiration, card.expiration) &&
                Objects.equals(balance, card.balance) &&
                status == card.status &&
                Objects.equals(user.getId(), card.user.getId()) &&
                Objects.equals(isArchived, card.isArchived);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, expiration, balance, status, user.getId(), isArchived);
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", expiration=" + expiration +
                ", balance=" + balance +
                ", status=" + status +
                ", user=" + user +
                ", isArchived=" + isArchived +
                '}';
    }
}
