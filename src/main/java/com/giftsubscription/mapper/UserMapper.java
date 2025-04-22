package com.giftsubscription.mapper;

import com.giftsubscription.dto.UserRegisterDTO;
import com.giftsubscription.dto.UserResponseDTO;
import com.giftsubscription.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "balance", constant = "0.0")
    @Mapping(target = "subscriptionType", constant = "STANDARD")
    User toEntity(UserRegisterDTO dto);

    UserResponseDTO toDto(User user);   // для ответа наружу
}
