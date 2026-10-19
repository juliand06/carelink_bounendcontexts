package com.carelink.application.security.usecase;

import java.time.LocalDateTime;

import com.carelink.application.security.dto.LoginRequest;
import com.carelink.application.security.dto.LoginResponse;
import com.carelink.domain.security.exception.InvalidCredentialsException;
import com.carelink.domain.security.exception.UserNotFoundException;
import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.port.repository.RefreshTokenRepository;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.domain.security.port.service.PasswordService;
import com.carelink.domain.security.port.service.TokenService;

public class LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final TokenService tokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final long accessTokenExpiration;

    public LoginUseCase(UserRepository userRepository,
                        PasswordService passwordService,
                        TokenService tokenService,
                        RefreshTokenRepository refreshTokenRepository,
                        long accessTokenExpiration) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.tokenService = tokenService;
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Usuario inactivo o bloqueado");
        }

        if (!passwordService.matches(request.password(), user.password())) {
            throw new InvalidCredentialsException();
        }

        String accessToken = tokenService.generateAccessToken(
                user.id().value(),
                user.email(),
                user.roles()
        );

        String refreshTokenStr = tokenService.generateRefreshToken();
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(7);

        RefreshToken refreshToken = RefreshToken.create(user.id(), refreshTokenStr, expiresAt);
        refreshTokenRepository.save(refreshToken);

        return LoginResponse.of(accessToken, refreshTokenStr, accessTokenExpiration / 1000);
    }
}
