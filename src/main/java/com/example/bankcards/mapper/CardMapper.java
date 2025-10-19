package com.example.bankcards.mapper;

import com.example.bankcards.dto.CardDto;
import com.example.bankcards.dto.CardDtoForCreation;
import com.example.bankcards.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

    @Mapping(target = "userId", source = "card.user.id")
    CardDto cardEntityToDto(Card card);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Card cardDtoToEntity(CardDtoForCreation cardDto);
}
