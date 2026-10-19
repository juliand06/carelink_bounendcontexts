package com.carelink.application.security.dto;

public record LoginRequest(
        String email,
        String password
) {}
