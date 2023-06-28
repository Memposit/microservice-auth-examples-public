package com.memposit.example.mfa.auth.service;

import com.memposit.example.mfa.auth.persistance.domain.AuthUser;
import com.memposit.example.mfa.auth.persistance.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final AuthUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        AuthUser user = repository.findByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("User not found with user name : " + username);
        }
        return new User(user.getName(), user.getPassword(), Collections.emptyList());
    }
}
