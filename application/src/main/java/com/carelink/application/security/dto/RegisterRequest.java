package com.carelink.application.security.dto;

public record RegisterRequest(
        String email,
        String password
) {}
