package com.example.bankcards.service.impl;

import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.Role;
import com.example.bankcards.entity.User;
import com.example.bankcards.exception.UserNotFoundException;
import com.example.bankcards.mapper.UserMapper;
import com.example.bankcards.repository.UserRepository;
import com.example.bankcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper userMapper;

    /**
     * Сохранение пользователя
     *
     * @return сохраненный пользователь
     */
    @Override
    public User save(User user) {
        return repository.save(user);
    }


    /**
     * Создание пользователя
     *
     * @return созданный пользователь
     */
    @Override
    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }

        return save(user);
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    @Override
    public User getByUsername(String username) throws UserNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

    }

    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    @Override
    public UserDetailsService userDetailsService() {
        return username -> {
            try {
                return getByUsername(username);
            } catch (UserNotFoundException exception) {
                throw new RuntimeException(exception.getMessage());
            }
        };
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    @Override
    public User getCurrentUser() throws UserNotFoundException {
        // Получение имени пользователя из контекста Spring Security
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    @Override
    @Transactional
    public Page<UserDto> findAll(Pageable pageable) {
        Long count = repository.count();
        Page<User> userPage = repository.findAll(pageable);
        List<UserDto> userDtoList = userPage.hasContent() ?
                userPage.getContent().stream()
                        .map(user -> userMapper.userEntityToDto(user))
                        .collect(Collectors.toList()) :
                Collections.emptyList();
        return new PageImpl(userDtoList, pageable, count);
    }

    @Override
    @Transactional
    public void changeUserStatusByUserId(UUID userId) throws UserNotFoundException {
        User user = repository.getReferenceById(userId);
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        if (Role.ROLE_USER.equals(user.getRole())) {
            repository.changeUserStatusByUserId(userId, Role.ROLE_ADMIN.name());
        } else {
            repository.changeUserStatusByUserId(userId, Role.ROLE_USER.name());
        }
    }
}
