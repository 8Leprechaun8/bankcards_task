package com.example.bankcards.entity;

public enum CardStatus {

    ACTIVE("Активна"),
    BLOCKED("Заблокирована"),
    EXPIRED("Истек срок"),
    WAITING_FOR_BLOCKING("Ожидает блокировки");

    private String statusValue;

    CardStatus(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return statusValue;
    }
}
