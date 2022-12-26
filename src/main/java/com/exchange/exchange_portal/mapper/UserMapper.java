package com.exchange.exchange_portal.mapper;

import com.exchange.exchange_portal.dto.UserDto;
import com.exchange.exchange_portal.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password",constant = "")
    UserDto map(UserEntity entity);

    UserEntity map(UserDto dto);
}