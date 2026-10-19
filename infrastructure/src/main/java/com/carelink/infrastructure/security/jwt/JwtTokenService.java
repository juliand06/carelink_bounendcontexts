package com.carelink.infrastructure.security.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.carelink.domain.security.model.aggregate.Role;
import com.carelink.domain.security.model.valueobject.RoleId;
import com.carelink.domain.security.port.service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenService implements TokenService {
    private final SecretKey secretKey;
    private final long accessTokenExpiration;

    public JwtTokenService(
            @Value("${jwt.secret:404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970}") String secret,
            @Value("${jwt.access-token-expiration:900000}") long accessTokenExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
    }

    @Override
    public String generateAccessToken(UUID userId, String email, Set<Role> roles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + accessTokenExpiration);

        List<String> roleAuthorities = roles.stream()
                .map(Role::authority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(userId.toString())
                .claim("email", email)
                .claim("roles", roleAuthorities)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public UUID validateAccessToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return UUID.fromString(claims.getSubject());
    }

    @Override
    public Set<Role> extractRoles(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            @SuppressWarnings("unchecked")
            List<String> roleAuthorities = claims.get("roles", List.class);
            if (roleAuthorities == null || roleAuthorities.isEmpty()) {
                return Set.of();
            }

            return roleAuthorities.stream()
                    .map(authority -> {
                        String name = authority.replace("ROLE_", "").toUpperCase();
                        return Role.restore(RoleId.generate(), name, authority);
                    })
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            return Set.of();
        }
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.get("email", String.class);
    }
}
