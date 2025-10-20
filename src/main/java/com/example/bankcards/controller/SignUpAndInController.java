package com.example.bankcards.controller;

import com.example.bankcards.dto.JwtAuthenticationResponseDto;
import com.example.bankcards.dto.SignInRequestDto;
import com.example.bankcards.dto.SignUpRequestDto;
import com.example.bankcards.exception.UserFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SignUpAndInController", description = "Контроллер по регистрации и входу в систему")
@RestController
@RequestMapping("/auth")
public class SignUpAndInController {

    @Autowired
    private AuthenticationService authenticationService;

    /**
     * Регистрация в системе
     *
     * @param request Данные пользователя
     * @return Токен
     */
    @Operation(summary = "Регистрация")
    @PostMapping("/sign-up")
    public ResponseEntity<JwtAuthenticationResponseDto> signUp(@Valid @RequestBody SignUpRequestDto request) {
        try {
            JwtAuthenticationResponseDto token = authenticationService.signUp(request);
            return new ResponseEntity<JwtAuthenticationResponseDto>(token, HttpStatus.OK);
        } catch (UserFoundException exception) {
            return new ResponseEntity<JwtAuthenticationResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Вход в систему
     *
     * @param request Данные пользователя
     * @return Токен
     */
    @Operation(summary = "Вход")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtAuthenticationResponseDto> signIn(@Valid @RequestBody SignInRequestDto request) {
        try {
            JwtAuthenticationResponseDto token = authenticationService.signIn(request);
            return new ResponseEntity<JwtAuthenticationResponseDto>(token, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            return new ResponseEntity<JwtAuthenticationResponseDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
