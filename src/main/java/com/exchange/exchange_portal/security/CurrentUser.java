package com.exchange.exchange_portal.security;

import com.exchange.exchange_portal.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private final UserEntity user;

    public CurrentUser(UserEntity user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public UserEntity getUser() {
        return user;
    }
}