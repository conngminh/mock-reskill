package com.example.mockreskill.model.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    public LoginResponse(String accessToken) {
        this.token = accessToken;
    }
}
