package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.entity.CardStatus;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.mapper.UserMapper;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Test
    public void saveTest() {
        User user = createUser();
        when(repository.save(user)).thenReturn(user);

        userService.save(user);

        verify(repository).save(user);
    }

    @Test
    public void createUser_whenUsernameExists() {
        User user = createUser();
        when(repository.existsByUsername(user.getUsername())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.create(user));

        assertEquals("Пользователь с таким именем уже существует", exception.getMessage());
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void createUser_whenPasswordExists() {
        User user = createUser();
        when(repository.existsByUsername(user.getUsername())).thenReturn(false);
        when(repository.existsByEmail(user.getEmail())).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> userService.create(user));

        assertEquals("Пользователь с таким email уже существует", exception.getMessage());
        verify(repository).existsByUsername(user.getUsername());
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void createUser_whenSuccess() {
        User user = createUser();
        when(repository.existsByUsername(user.getUsername())).thenReturn(false);
        when(repository.existsByEmail(user.getEmail())).thenReturn(false);
        when(repository.save(user)).thenReturn(user);

        assertEquals(user, userService.create(user));

        verify(repository).existsByUsername(user.getUsername());
        verify(repository).existsByEmail(user.getEmail());
        verify(repository).save(user);
    }

    @Test
    public void getByUsernameTest_whenSuccess() throws UserNotFoundException {
        User user = createUser();
        when(repository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertEquals(user, userService.getByUsername(user.getUsername()));

        verify(repository).findByUsername(user.getUsername());
    }

    @Test
    public void getByUsernameTest_whenException() throws UserNotFoundException {
        User user = createUser();
        when(repository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> userService.getByUsername(user.getUsername()));

        assertEquals("Пользователь не найден", exception.getMessage());
        verify(repository).findByUsername(user.getUsername());
    }

    private User createUser() {
        User user = new User();
        user.setArchived(false);
        user.setEmail("aaa@mail.ru");
        user.setId(UUID.fromString("773ba8d9-182a-494c-bdc0-34fd62059f4a"));
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword("123");
        user.setFirstName("Aaa");
        user.setSecondName("Bbb");
        user.setLastName("Ddd");
        user.setUsername("fff");
        Card card = createCard();
        user.setCardList(List.of(card));
        return user;
    }

    private Card createCard() {
        Card card = new Card();
        card.setId(UUID.fromString("44e002e7-f998-4abb-ae45-989d763976e1"));
        card.setArchived(false);
        card.setUser(null);
        card.setStatus(CardStatus.ACTIVE);
        card.setBalance(100.0);
        card.setExpiration(LocalDateTime.of(2030, 4,17,10,10));
        card.setNumber("1234 5678 0101 0101");
        return card;
    }
}
