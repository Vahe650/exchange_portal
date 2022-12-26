package com.exchange.exchange_portal.controller;

import com.exchange.exchange_portal.dto.auth.UserAuthDto;
import com.exchange.exchange_portal.dto.auth.UserAuthResponseDto;
import com.exchange.exchange_portal.entity.UserEntity;
import com.exchange.exchange_portal.service.UserService;
import com.exchange.exchange_portal.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping
    public ResponseEntity<UserAuthResponseDto> auth(@RequestBody UserAuthDto userAuthDto) {
        UserEntity entity = userService.findByUsername(userAuthDto.getUsername());
        if (entity != null && passwordEncoder.matches(userAuthDto.getPassword(), entity.getPassword())) {
            log.info("User with username {} was found", entity.getUsername());
            return ResponseEntity.ok(new UserAuthResponseDto
                    (jwtTokenUtil.generateToken(entity.getUsername()), entity.getUsername())
            );
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}