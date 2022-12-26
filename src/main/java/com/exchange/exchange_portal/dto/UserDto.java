package com.exchange.exchange_portal.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private UserType userType;
}