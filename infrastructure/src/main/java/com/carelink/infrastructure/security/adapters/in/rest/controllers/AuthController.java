package com.carelink.infrastructure.security.adapters.in.rest.controllers;

import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carelink.application.security.dto.ChangePasswordRequest;
import com.carelink.application.security.dto.LoginRequest;
import com.carelink.application.security.dto.LoginResponse;
import com.carelink.application.security.dto.RefreshTokenRequest;
import com.carelink.application.security.dto.RegisterRequest;
import com.carelink.application.security.dto.UserResponse;
import com.carelink.application.security.usecase.ChangePasswordUseCase;
import com.carelink.application.security.usecase.GetCurrentUserUseCase;
import com.carelink.application.security.usecase.LoginUseCase;
import com.carelink.application.security.usecase.LogoutUseCase;
import com.carelink.application.security.usecase.RefreshTokenUseCase;
import com.carelink.application.security.usecase.RegisterUserUseCase;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUseCase loginUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final LogoutUseCase logoutUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;

    public AuthController(RegisterUserUseCase registerUserUseCase,
                          LoginUseCase loginUseCase,
                          RefreshTokenUseCase refreshTokenUseCase,
                          LogoutUseCase logoutUseCase,
                          GetCurrentUserUseCase getCurrentUserUseCase,
                          ChangePasswordUseCase changePasswordUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUseCase = loginUseCase;
        this.refreshTokenUseCase = refreshTokenUseCase;
        this.logoutUseCase = logoutUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
        this.changePasswordUseCase = changePasswordUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = registerUserUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = loginUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = refreshTokenUseCase.execute(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody(required = false) RefreshTokenRequest request) {
        if (request != null && request.refreshToken() != null) {
            logoutUseCase.execute(request.refreshToken());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        UserResponse response = getCurrentUserUseCase.execute(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest request,
                                               Authentication authentication) {
        UUID userId = (UUID) authentication.getPrincipal();
        changePasswordUseCase.execute(userId, request);
        return ResponseEntity.noContent().build();
    }
}
