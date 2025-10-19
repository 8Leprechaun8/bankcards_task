package com.example.bankcards.service.impl;

import com.example.bankcards.dto.JwtAuthenticationResponseDto;
import com.example.bankcards.dto.SignInRequestDto;
import com.example.bankcards.dto.SignUpRequestDto;
import com.example.bankcards.entity.Role;
import com.example.bankcards.exception.UserFoundException;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.service.AuthenticationService;
import com.example.bankcards.service.JwtService;
import com.example.bankcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.bankcards.entity.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static String SIGNUP_SAME_LOGIN = "При регистрации найден пользователь с похожим логином";
    private static String SIGNIN_SAME_LOGIN = "При входе в систему не найден пользователь с похожим логином";
    private static String SIGNIN_SAME_PASSWORD = "При входе в систему не найден пользователь с похожим паролем";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Регистрация в системе
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponseDto signUp(SignUpRequestDto request) throws UserFoundException {
        try {
            String username = request.getUsername();
            userService.getByUsername(username);
            throw new UserFoundException(SIGNUP_SAME_LOGIN);
        } catch (UserNotFoundException exception) {
            User user = new User(
                    null,
                    request.getFirstName(),
                    request.getSecondName(),
                    request.getLastName(),
                    request.getUsername(),
                    request.getPassword(),
                    Role.ROLE_USER,
                    request.getEmail(),
                    false
            );
            userService.create(user);
            String jwt = jwtService.generateToken(user);
            return new JwtAuthenticationResponseDto(jwt);
        }
    }

    /**
     * Вход в систему
     *
     * @param request данные пользователя
     * @return токен
     */
    @Override
    public JwtAuthenticationResponseDto signIn(SignInRequestDto request) throws UserNotFoundException {
        User user = null;
        try {
            String username = request.getUsername();
            user = userService.getByUsername(username);
        } catch (UserNotFoundException exception) {
            throw new UserNotFoundException(SIGNIN_SAME_LOGIN);
        }
        if (!user.getPassword().equals(request.getPassword())) {
            throw new UserNotFoundException(SIGNIN_SAME_PASSWORD);
        }
        String jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponseDto(jwt);
    }
}
