package com.carelink.infrastructure.handoffassignment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateHandoffAssignmentRequest(
        @NotNull(message = "handoffId is required")
        UUID handoffId,

        @NotNull(message = "specialistId is required")
        UUID specialistId
) {
}
