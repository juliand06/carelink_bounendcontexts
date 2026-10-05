package com.carelink.infrastructure.specialistcredential.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateSpecialistCredentialRequest(
        @NotNull(message = "academicProgramId is required")
        UUID academicProgramId,

        @NotNull(message = "specialistId is required")
        UUID specialistId,

        @NotNull(message = "degreeTitle is required")
        @Size(max = 100, message = "degreeTitle must have at most 100 characters")
        String degreeTitle,

        @NotNull(message = "institution is required")
        @Size(max = 100, message = "institution must have at most 100 characters")
        String institution,

        @NotNull(message = "isVerified is required")
        Boolean isVerified,

        @Size(max = 60, message = "resolutionCode must have at most 60 characters")
        String resolutionCode,

        @NotNull(message = "countryId is required")
        UUID countryId
) {
}
