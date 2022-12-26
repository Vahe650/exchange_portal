package com.exchange.exchange_portal.config.security;

import com.exchange.exchange_portal.dto.UserType;
import com.exchange.exchange_portal.security.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;

    private final JwtAuthenticationEntryPoint unauthorizedHandler;
    private final JwtAuthenticationTokenFilter filter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable().cors().and()
                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()


                .authorizeRequests()
                .antMatchers("/auth", "/users")
                .permitAll()
                .anyRequest()
                .hasAuthority(UserType.USER.name());
        // Custom JWT based security filter
        http
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


}