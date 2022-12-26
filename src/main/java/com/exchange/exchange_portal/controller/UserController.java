package com.exchange.exchange_portal.controller;

import com.exchange.exchange_portal.dto.UserDto;
import com.exchange.exchange_portal.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;


    @PostMapping
    public UserDto register(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }
}