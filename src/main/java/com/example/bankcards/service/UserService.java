package com.example.bankcards.service;

import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User create(User user);

    User save(User user);

    User getByUsername(String username) throws UserNotFoundException;

    User getCurrentUser() throws UserNotFoundException;

    UserDetailsService userDetailsService();

    Page<UserDto> findAll(Pageable pageable);

    void changeUserStatusByUserId(UUID userId) throws UserNotFoundException;
}
