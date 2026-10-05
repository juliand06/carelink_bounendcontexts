package com.carelink.infrastructure.appointment.adapters.in.rest.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateAppointmentRequest(
        @NotNull(message = "healthRecordId is required")
        UUID healthRecordId,

        @NotNull(message = "specialistId is required")
        UUID specialistId,

        @NotNull(message = "appointmentTypeId is required")
        UUID appointmentTypeId,

        @NotNull(message = "startedAt is required")
        LocalDateTime startedAt,

        LocalDateTime endedAt,

        String chiefComplaint,

        String currentCondition,

        @NotNull(message = "modalityId is required")
        UUID modalityId,

        @NotNull(message = "statusId is required")
        UUID statusId,

        UUID updatedBy
) {
}
