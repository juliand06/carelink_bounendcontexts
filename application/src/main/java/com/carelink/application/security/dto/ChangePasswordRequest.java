package com.carelink.application.security.dto;

public record ChangePasswordRequest(
        String currentPassword,
        String newPassword
) {}
