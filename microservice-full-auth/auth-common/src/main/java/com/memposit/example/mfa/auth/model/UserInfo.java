package com.memposit.example.mfa.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;

@Data
@AllArgsConstructor
public class UserInfo implements Principal {

    private String name;
    private String token;

    @Override
    public String getName() {
        return name;
    }
}
