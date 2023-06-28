package com.memposit.example.mapia.auth.persistance.repository;

import com.memposit.example.mapia.auth.persistance.domain.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

    AuthUser findByName(String name);
}
