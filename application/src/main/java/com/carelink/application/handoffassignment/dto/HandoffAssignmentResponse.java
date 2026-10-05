package com.carelink.application.handoffassignment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record HandoffAssignmentResponse(
        UUID id,
        UUID handoffId,
        UUID specialistId,
        LocalDateTime assignedAt
) {
}
