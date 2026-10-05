package com.carelink.application.sessionnote.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record SessionNoteResponse(
        UUID id,
        UUID appointmentId,
        UUID specialistId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String supplementaryNotes,
        LocalDateTime signedAt,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
