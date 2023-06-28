package com.memposit.example.mfa.auth.model;

import lombok.Data;

@Data
public class LoginRequest {

  private String userId;
  private String password;

}
