package com.carelink.domain.security.port.service;

import java.util.Set;
import java.util.UUID;

import com.carelink.domain.security.model.aggregate.Role;

public interface TokenService {
    String generateAccessToken(UUID userId, String email, Set<Role> roles);
    String generateRefreshToken();
    UUID validateAccessToken(String token);
    Set<Role> extractRoles(String token);
}
