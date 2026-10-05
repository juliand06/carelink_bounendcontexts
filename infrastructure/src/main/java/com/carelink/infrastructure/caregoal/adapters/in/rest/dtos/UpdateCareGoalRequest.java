package com.carelink.infrastructure.caregoal.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateCareGoalRequest(
        @NotNull(message = "carePlanId is required")
        UUID carePlanId,

        @NotNull(message = "goalDescription is required")
        String goalDescription,

        LocalDate targetDate,

        LocalDateTime achievedAt,

        String progressNotes,

        @NotNull(message = "careGoalStatusId is required")
        UUID careGoalStatusId
) {
}
