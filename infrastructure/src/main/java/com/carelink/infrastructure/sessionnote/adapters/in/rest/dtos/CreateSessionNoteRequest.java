package com.carelink.infrastructure.sessionnote.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateSessionNoteRequest(
        @NotNull(message = "appointmentId is required")
        UUID appointmentId,

        @NotNull(message = "specialistId is required")
        UUID specialistId,

        String subjective,

        String objective,

        String assessment,

        String plan,

        String supplementaryNotes,

        LocalDateTime signedAt
) {
}
