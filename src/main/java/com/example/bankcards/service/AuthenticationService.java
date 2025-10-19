package com.example.bankcards.service;

import com.example.bankcards.dto.JwtAuthenticationResponseDto;
import com.example.bankcards.dto.SignInRequestDto;
import com.example.bankcards.dto.SignUpRequestDto;
import com.example.bankcards.exception.UserFoundException;
import com.example.bankcards.exception.UserNotFoundException;

public interface AuthenticationService {

    /**
     * Регистрация в системе
     *
     * @param request Данные пользователя
     * @return Токен
     */
    JwtAuthenticationResponseDto signUp(SignUpRequestDto request) throws UserFoundException;

    /**
     * Вход в систему
     *
     * @param request Данные пользователя
     * @return Токен
     */
    JwtAuthenticationResponseDto signIn(SignInRequestDto request) throws UserNotFoundException;
}
