package com.example.bankcards.service;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardDtoForCreation;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.CardStatusNotActiveException;
import com.example.bankcards.exception.SendingMoneyIsProhibitedException;
import com.example.bankcards.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BankcardsService {

    Page<CardDto> findAll(Pageable pageable);

    Page<CardDto> findAllForUser(UUID userId, Pageable pageable);

    Double getBalanceForCardByUser(UUID id, String number) throws CardNotFoundException;

    void askForCardBlockingByUser(UUID id, String number) throws CardNotFoundException, CardStatusNotActiveException;

    void sendSumFromOneCardToAnotherOne(UUID id, String numberFrom, String numberTo, Double sum) throws CardStatusNotActiveException, CardNotFoundException, SendingMoneyIsProhibitedException;

    void createCard(CardDtoForCreation cardDto) throws UserNotFoundException;
}
