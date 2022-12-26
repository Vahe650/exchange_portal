package com.exchange.exchange_portal.security;

import com.exchange.exchange_portal.entity.UserEntity;
import com.exchange.exchange_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byEmail = userRepository.findByUsername(username);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("Username does not exists.");
        }
        return new CurrentUser(byEmail.get());
    }
}