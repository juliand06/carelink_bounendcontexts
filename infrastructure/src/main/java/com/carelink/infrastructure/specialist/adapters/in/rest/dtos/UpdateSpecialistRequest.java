package com.carelink.infrastructure.specialist.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSpecialistRequest(
        @NotNull(message = "idDocumentTypeId is required")
        UUID idDocumentTypeId,

        @NotNull(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotNull(message = "firstName is required")
        @Size(max = 60, message = "firstName must have at most 60 characters")
        String firstName,

        @NotNull(message = "lastName is required")
        @Size(max = 60, message = "lastName must have at most 60 characters")
        String lastName,

        @NotNull(message = "specialistRoleId is required")
        UUID specialistRoleId,

        @NotNull(message = "professionalLicense is required")
        @Size(max = 100, message = "professionalLicense must have at most 100 characters")
        String professionalLicense,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "municipalityId is required")
        UUID municipalityId
) {
}
