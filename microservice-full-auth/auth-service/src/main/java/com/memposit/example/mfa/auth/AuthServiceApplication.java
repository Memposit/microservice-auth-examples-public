package com.memposit.example.mfa.auth;

import com.memposit.example.mfa.auth.persistance.domain.AuthUser;
import com.memposit.example.mfa.auth.persistance.repository.AuthUserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }

    @Bean
    ApplicationRunner init(AuthUserRepository repository, PasswordEncoder passwordEncoder) {
        return args -> Stream.of("test", "admin")
                .forEach(name -> {
                    AuthUser user = new AuthUser();
                    user.setName(name);
                    user.setPassword(passwordEncoder.encode(name));
                    repository.save(user);
                });
    }
}