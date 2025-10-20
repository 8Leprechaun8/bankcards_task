package com.example.bankcards.controller;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardDtoForCreation;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.*;
import com.example.bankcards.service.BankcardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@Tag(name = "BankcardsController", description = "Контроллер по работе с банковскими картами")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/bankcards")
public class BankcardsController {

    @Autowired
    private BankcardsService bankcardsService;

    /**
     * Просмотр вообще всех карт админом
     *
     * @return Возвращает список всех неудаленных карт
     */
    @Operation(summary = "Поиск всех карт админом")
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<CardDto> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "10") int size,
                                 @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                 @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return bankcardsService.findAll(pageable);
    }

    /**
     * Просмотр всех карт конкретного пользователя админом
     *
     * @return Возвращает список всех неудаленных карт конкретного пользователя
     */
    @Operation(summary = "Поиск всех карт админом для конкретного пользователя")
    @GetMapping("/admin/all/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<CardDto> findAllForUser(@PathVariable(name = "userId") UUID userId,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                        @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return bankcardsService.findAllForUser(userId, pageable);
    }

    @Operation(summary = "Поиск всех своих карт как пользователем, так и админом")
    public Page<CardDto> findAllForUserByUser(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                        @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                        @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return bankcardsService.findAllForUser(user.getId(), pageable);
    }

    @Operation(summary = "Получение баланса по карте пользователем")
    @GetMapping("/user/all/mycards/balance")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Map<String, Object>> getBalanceForCardByUser(@RequestParam(name = "number") String number) throws CardNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Double balance = bankcardsService.getBalanceForCardByUser(user.getId(), number);
        return new ResponseEntity<Map<String, Object>>(
                Map.of(
                        "number", number,
                        "balance", balance
                ), HttpStatus.OK
        );
    }

    @Operation(summary = "Отправка запроса на блокировку карты пользователем")
    @PutMapping("/user/all/mycards/blocking")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> askForCardBlockingByUser(@RequestParam(name = "number") String number) throws CardNotFoundException, CardStatusNotActiveException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bankcardsService.askForCardBlockingByUser(user.getId(), number);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Перевод денег с карты на карту как пользователем, так и админом")
    @PutMapping("/user-and-admin/all/mycards/sending-money")
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> sendSumFromOneCardToAnotherOne(@RequestParam(name = "numberFrom") String numberFrom,
                                                           @RequestParam(name = "numberTo") String numberTo,
                                                           @RequestParam(name = "sum") Double sum) throws SendingMoneyIsProhibitedException, CardStatusNotActiveException, CardNotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bankcardsService.sendSumFromOneCardToAnotherOne(user.getId(), numberFrom, numberTo, sum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Создание карты админом для пользователя")
    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CardDto> createCard(@Valid @RequestBody CardDtoForCreation cardDto) throws UserNotFoundException {
        bankcardsService.createCard(cardDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Блокировка карты админом")
    @PutMapping("/admin/blocking")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CardDto> blockCard(@RequestParam(name = "id") UUID id) throws CardStatusNotWaitingForBlockingException, CardNotFoundException {
        bankcardsService.blockCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Активация карты админом")
    @PutMapping("/admin/activating")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CardDto> activateCard(@RequestParam(name = "id") UUID id) throws CardNotFoundException, CardStatusNotWaitingForBlockingException {
        bankcardsService.activateCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Удаление карты админом")
    @DeleteMapping("/admin/deleting")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CardDto> deleteCard(@RequestParam(name = "id") UUID id) throws CardNotFoundException {
        bankcardsService.deleteCard(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Object getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal;
    }
}