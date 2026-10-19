package com.carelink.application.security.usecase;

import com.carelink.application.security.dto.LoginResponse;
import com.carelink.application.security.dto.RefreshTokenRequest;
import com.carelink.domain.security.exception.InvalidCredentialsException;
import com.carelink.domain.security.exception.UserNotFoundException;
import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.model.aggregate.User;
import com.carelink.domain.security.port.repository.RefreshTokenRepository;
import com.carelink.domain.security.port.repository.UserRepository;
import com.carelink.domain.security.port.service.TokenService;

public class RefreshTokenUseCase {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final long accessTokenExpiration;

    public RefreshTokenUseCase(RefreshTokenRepository refreshTokenRepository,
                              UserRepository userRepository,
                              TokenService tokenService,
                              long accessTokenExpiration) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.accessTokenExpiration = accessTokenExpiration;
    }

    public LoginResponse execute(RefreshTokenRequest request) {
        RefreshToken token = refreshTokenRepository.findByToken(request.refreshToken())
                .orElseThrow(() -> new InvalidCredentialsException("Refresh token inválido"));

        if (!token.isValid()) {
            throw new InvalidCredentialsException("Refresh token expirado o revocado");
        }

        User user = userRepository.findById(token.userId())
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        if (!user.isActive()) {
            throw new InvalidCredentialsException("Usuario inactivo o bloqueado");
        }

        String newAccessToken = tokenService.generateAccessToken(
                user.id().value(),
                user.email(),
                user.roles()
        );

        return LoginResponse.of(newAccessToken, token.token(), accessTokenExpiration / 1000);
    }
}
