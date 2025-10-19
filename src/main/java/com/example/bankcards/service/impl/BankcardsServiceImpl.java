package com.example.bankcards.service.impl;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardDtoForCreation;
import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.CardNotFoundException;
import com.example.bankcards.exception.CardStatusNotActiveException;
import com.example.bankcards.exception.SendingMoneyIsProhibitedException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.mapper.CardMapper;
import com.example.bankcards.repository.CardRepository;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.BankcardsService;
import com.example.bankcards.specification.CardSpecification;
import com.example.bankcards.specification.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BankcardsServiceImpl implements BankcardsService {

    private static double EPS = 0.00001;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CardMapper cardMapper;

    @Override
    public void createCard(CardDtoForCreation cardDto) throws UserNotFoundException {
        Card card = cardMapper.cardDtoToEntity(cardDto);
        User user = userRepository.getReferenceById(cardDto.getUserId());
        if (user == null) {
            throw new UserNotFoundException("Пользователь, которому будет соответствовать карта, не найден");
        }
        card.setUser(user);
        cardRepository.save(card);
    }

    @Override
    @Transactional
    public Page<CardDto> findAll(Pageable pageable) {
        CardSpecification spec =
                new CardSpecification(new SearchCriteria("", "", ""));
        return findAll(spec, pageable);
    }

    @Override
    @Transactional
    public Page<CardDto> findAllForUser(UUID userId, Pageable pageable) {
        CardSpecification spec =
                new CardSpecification(new SearchCriteria("userId", "equals", userId));
        return findAll(spec, pageable);
    }

    private Page<CardDto> findAll(CardSpecification spec, Pageable pageable) {
        Long count = cardRepository.count(spec);
        Page<Card> cardPage = cardRepository.findAll(spec, pageable);
        List<CardDto> cardDtoList = cardPage.hasContent() ?
                cardPage.getContent().stream()
                        .map(card -> cardMapper.cardEntityToDto(card))
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        return new PageImpl(cardDtoList, pageable, count);
    }

    @Override
    @Transactional
    public Double getBalanceForCardByUser(UUID id, String number) throws CardNotFoundException {
        Double balance = cardRepository.getBalanceForCardByUser(id, number);
        if (balance == null) {
            throw new CardNotFoundException("Не найдена карта");
        }
        return balance;
    }

    @Override
    @Transactional
    public void askForCardBlockingByUser(UUID id, String number) throws CardNotFoundException, CardStatusNotActiveException {
        Card card = cardRepository.findByUserIdAndNumberAndIsArchived(id, number, false);
        if (card != null) {
            if (!card.getStatus().equals(CardStatus.ACTIVE)) {
                throw new CardStatusNotActiveException("Статус карты не активен");
            }
            cardRepository.setStatusWaitingForBlocking(id, number);
        } else {
            throw new CardNotFoundException("Не найдена карта");
        }
    }

    @Override
    @Transactional
    public void sendSumFromOneCardToAnotherOne(UUID id, String numberFrom, String numberTo, Double sum) throws CardStatusNotActiveException, CardNotFoundException, SendingMoneyIsProhibitedException {
        Card cardFrom = cardRepository.findByUserIdAndNumberAndIsArchived(id, numberFrom, false);
        validateCardForSendingMoney(cardFrom);
        Card cardTo = cardRepository.findByUserIdAndNumberAndIsArchived(id, numberTo, false);
        validateCardForSendingMoney(cardTo);
        double diff = cardFrom.getBalance() - sum;
        if (cardFrom.getBalance() <= 0 || (diff < 0) && (Math.abs(diff) > EPS)) {
            throw new SendingMoneyIsProhibitedException("Сумма на карте " + numberFrom + " слишком мала для перевода");
        } else if ((diff < 0) && (Math.abs(diff) < EPS)) {
            sum = cardFrom.getBalance();
        }
        cardRepository.setBalanceOnCard(id, numberFrom, cardFrom.getBalance() - sum, false);
        cardRepository.setBalanceOnCard(id, numberTo, cardTo.getBalance() + sum, false);
    }

    private void validateCardForSendingMoney(Card card) throws CardStatusNotActiveException, CardNotFoundException {
        if (card != null) {
            if (!card.getStatus().equals(CardStatus.ACTIVE)) {
                throw new CardStatusNotActiveException("Статус карты не активен");
            }
        } else {
            throw new CardNotFoundException("Не найдена карта");
        }
    }
}
