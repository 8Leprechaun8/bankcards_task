package com.example.bankcards.exception;

public class CardStatusNotActiveException extends Exception {
    public CardStatusNotActiveException(String message) {
        super(message);
    }
}
