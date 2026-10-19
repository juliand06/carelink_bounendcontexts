package com.carelink.application.security.usecase;

import com.carelink.domain.security.model.aggregate.RefreshToken;
import com.carelink.domain.security.port.repository.RefreshTokenRepository;

public class LogoutUseCase {
    private final RefreshTokenRepository refreshTokenRepository;

    public LogoutUseCase(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    public void execute(String refreshTokenStr) {
        if (refreshTokenStr == null || refreshTokenStr.isBlank()) {
            return;
        }
        refreshTokenRepository.findByToken(refreshTokenStr).ifPresent(token -> {
            token.revoke();
            refreshTokenRepository.save(token);
        });
    }
}
