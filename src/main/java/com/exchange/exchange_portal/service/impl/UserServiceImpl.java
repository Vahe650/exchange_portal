package com.exchange.exchange_portal.service.impl;

import com.exchange.exchange_portal.dto.UserDto;
import com.exchange.exchange_portal.dto.UserType;
import com.exchange.exchange_portal.entity.UserEntity;
import com.exchange.exchange_portal.mapper.UserMapper;
import com.exchange.exchange_portal.repository.UserRepository;
import com.exchange.exchange_portal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;


    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity entity = userMapper.map(userDto);
        entity.setUserType(UserType.USER);
        entity.setPassword(encoder.encode(userDto.getPassword()));
        entity = userRepository.save(entity);
        return userMapper.map(entity);
    }
}
