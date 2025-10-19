package com.example.bankcards.service;

import com.example.bankcards.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    String generateToken(User user);

    String extractUserName(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
