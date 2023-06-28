package com.memposit.example.mfa.auth.persistance.repository;

import com.memposit.example.mfa.auth.persistance.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByName(String name);
}
