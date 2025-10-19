package com.example.bankcards.exception;

public class SendingMoneyIsProhibitedException extends Exception {
    public SendingMoneyIsProhibitedException(String message) {
        super(message);
    }
}
