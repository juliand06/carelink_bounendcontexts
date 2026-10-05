package com.carelink.infrastructure.careplan.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCarePlanRequest(
        @NotNull(message = "appointmentId is required")
        UUID appointmentId,

        @NotNull(message = "specialistId is required")
        UUID specialistId,

        @NotNull(message = "planTitle is required")
        @Size(max = 200, message = "planTitle must have at most 200 characters")
        String planTitle,

        String planDescription,

        @NotNull(message = "startDate is required")
        LocalDate startDate,

        LocalDate projectedEndDate,

        @NotNull(message = "carePlanStatusId is required")
        UUID carePlanStatusId
) {
}
