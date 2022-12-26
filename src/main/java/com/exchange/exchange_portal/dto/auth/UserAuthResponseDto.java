package com.exchange.exchange_portal.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAuthResponseDto {
    private String token;
    private String userName;
}