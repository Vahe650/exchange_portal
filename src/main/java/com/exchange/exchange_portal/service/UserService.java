package com.exchange.exchange_portal.service;

import com.exchange.exchange_portal.dto.UserDto;
import com.exchange.exchange_portal.entity.UserEntity;

public interface UserService {

    UserEntity findByUsername(String username);

    UserDto createUser(UserDto userDto);
}
