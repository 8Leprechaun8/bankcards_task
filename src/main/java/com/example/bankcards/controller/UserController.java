package com.example.bankcards.controller;

import com.example.bankcards.dto.UserDto;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "UserController", description = "Контроллер по управлению пользователями")
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Поиск всех пользователей админом")
    @GetMapping("/admin/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Page<UserDto> findAll(@RequestParam(name = "page", defaultValue = "0") int page,
                                 @RequestParam(name = "size", defaultValue = "10") int size,
                                 @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
                                 @RequestParam(name = "sortDirection", defaultValue = "asc") String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        return userService.findAll(pageable);
    }

    @Operation(summary = "Метод смены роли у пользователя админом")
    @GetMapping("/admin/change-status/{userId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UUID> changeUserStatusByUserId(@PathVariable(name = "userId") UUID userId) throws UserNotFoundException {
        userService.changeUserStatusByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
