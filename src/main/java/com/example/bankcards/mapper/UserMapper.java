package com.example.bankcards.mapper;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.UserDto;
import com.example.bankcards.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CardMapper cardMapper = new CardMapperImpl();

    @Mapping(target = "cardList", expression = "java(cardListEntityToDto(user))")
    UserDto userEntityToDto(User user);

    default List<CardDto> cardListEntityToDto(User user) {
        if (user == null || user.getCardList() == null || user.getCardList().isEmpty()) {
            return null;
        }
        return user.getCardList().stream()
                .map(card -> cardMapper.cardEntityToDto(card))
                .collect(Collectors.toList());
    }
}
