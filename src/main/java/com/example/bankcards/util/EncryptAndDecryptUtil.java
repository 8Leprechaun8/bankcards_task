package com.example.bankcards.util;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardDtoForCreation;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.User;
import org.jasypt.util.text.BasicTextEncryptor;

import java.util.stream.Collectors;

public class EncryptAndDecryptUtil {

    private static final BasicTextEncryptor textEncryptor;
    private static final String password;

    static {
        textEncryptor = new BasicTextEncryptor();
        password = "123";
        textEncryptor.setPasswordCharArray(password.toCharArray());
    }

    public static String encrypt(String number) {
        if (number == null) {
            return null;
        }
        return textEncryptor.encrypt(number);
    }

    public static String decrypt(String number) {
        if (number == null) {
            return null;
        }
        return textEncryptor.decrypt(number);
    }

    public static CardDtoForCreation encrypt(CardDtoForCreation card) {
        if (card == null || card.getNumber() == null) {
            return card;
        }
        card.setNumber(encrypt(card.getNumber()));
        return card;
    }

    public static Card encrypt(Card card) {
        if (card == null || card.getNumber() == null) {
            return card;
        }
        card.setNumber(encrypt(card.getNumber()));
        return card;
    }

    public static CardDto encrypt(CardDto card) {
        if (card == null || card.getNumber() == null) {
            return card;
        }
        card.setNumber(encrypt(card.getNumber()));
        return card;
    }

    public static Card decrypt(Card card) {
        if (card == null || card.getNumber() == null) {
            return card;
        }
        card.setNumber(decrypt(card.getNumber()));
        return card;
    }

    public static CardDto decrypt(CardDto card) {
        if (card == null || card.getNumber() == null) {
            return card;
        }
        card.setNumber(decrypt(card.getNumber()));
        return card;
    }

    public static User encrypt(User user) {
        if (user == null || user.getCardList() == null || user.getCardList().isEmpty()) {
            return user;
        }
        user.setCardList(user.getCardList().stream()
                .map(card -> encrypt(card))
                .collect(Collectors.toList())
        );
        return user;
    }

    public static UserDto encrypt(UserDto user) {
        if (user == null || user.getCardList() == null || user.getCardList().isEmpty()) {
            return user;
        }
        user.setCardList(user.getCardList().stream()
                .map(card -> encrypt(card))
                .collect(Collectors.toList())
        );
        return user;
    }

    public static User decrypt(User user) {
        if (user == null || user.getCardList() == null || user.getCardList().isEmpty()) {
            return user;
        }
        user.setCardList(user.getCardList().stream()
                .map(card -> decrypt(card))
                .collect(Collectors.toList())
        );
        return user;
    }

    public static UserDto decrypt(UserDto user) {
        if (user == null || user.getCardList() == null || user.getCardList().isEmpty()) {
            return user;
        }
        user.setCardList(user.getCardList().stream()
                .map(card -> decrypt(card))
                .collect(Collectors.toList())
        );
        return user;
    }
}
