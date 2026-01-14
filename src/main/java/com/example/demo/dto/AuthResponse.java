package com.example.demo.dto;

public class AuthResponse {
    private String token;

    // Constructor is MANDATORY for the Controller to use 'new AuthResponse(token)'
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getters and Setters are MANDATORY for Postman to see the JSON
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}