package com.carelink.infrastructure.handoffstatusaudit.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateHandoffStatusAuditRequest(
        @NotNull(message = "handoffId is required")
        UUID handoffId,

        @NotNull(message = "handoffStatusId is required")
        UUID handoffStatusId
) {
}
