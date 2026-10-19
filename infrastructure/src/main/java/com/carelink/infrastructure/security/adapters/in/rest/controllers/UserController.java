package com.carelink.infrastructure.security.adapters.in.rest.controllers;

import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carelink.application.security.dto.AssignRoleRequest;
import com.carelink.application.security.dto.UserResponse;
import com.carelink.application.security.usecase.AssignRoleUseCase;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final AssignRoleUseCase assignRoleUseCase;

    public UserController(AssignRoleUseCase assignRoleUseCase) {
        this.assignRoleUseCase = assignRoleUseCase;
    }

    @PutMapping("/{userId}/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> assignRole(@PathVariable UUID userId,
                                                   @Valid @RequestBody AssignRoleRequest request) {
        UserResponse response = assignRoleUseCase.execute(userId, request);
        return ResponseEntity.ok(response);
    }
}
