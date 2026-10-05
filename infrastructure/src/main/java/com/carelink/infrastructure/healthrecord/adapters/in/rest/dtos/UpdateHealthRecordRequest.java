package com.carelink.infrastructure.healthrecord.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateHealthRecordRequest(
        @NotNull(message = "clientId is required")
        UUID clientId,

        @NotNull(message = "recordCode is required")
        @Size(max = 50, message = "recordCode must have at most 50 characters")
        String recordCode,

        LocalDateTime openedAt,

        LocalDateTime closedAt,

        @NotNull(message = "statusId is required")
        UUID statusId
) {
}
