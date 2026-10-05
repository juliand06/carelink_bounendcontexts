package com.carelink.application.caregoal.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CareGoalResponse(
        UUID id,
        UUID carePlanId,
        String goalDescription,
        LocalDate targetDate,
        LocalDateTime achievedAt,
        String progressNotes,
        UUID careGoalStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
