package com.carelink.infrastructure.appointmentmodality.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAppointmentModalityRequest(
        @NotNull(message = "code is required")
        @Size(max = 20, message = "code must have at most 20 characters")
        String code,

        @NotNull(message = "label is required")
        @Size(max = 50, message = "label must have at most 50 characters")
        String label,

        @NotNull(message = "active is required")
        Boolean active
) {
}
