package com.carelink.infrastructure.client.adapters.in.rest.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClientRequest(
        @NotNull(message = "idDocumentTypeId is required")
        UUID idDocumentTypeId,

        @NotNull(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotNull(message = "firstName is required")
        @Size(max = 50, message = "firstName must have at most 50 characters")
        String firstName,

        @Size(max = 50, message = "middleName must have at most 50 characters")
        String middleName,

        @NotNull(message = "lastName is required")
        @Size(max = 50, message = "lastName must have at most 50 characters")
        String lastName,

        @Size(max = 50, message = "secondLastName must have at most 50 characters")
        String secondLastName,

        @NotNull(message = "birthDate is required")
        LocalDate birthDate,

        @NotNull(message = "biologicalSexId is required")
        UUID biologicalSexId,

        @NotNull(message = "genderIdentityId is required")
        UUID genderIdentityId,

        @NotNull(message = "email is required")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        @Size(max = 30, message = "mobilePhone must have at most 30 characters")
        String mobilePhone,

        @Size(max = 250, message = "homeAddress must have at most 250 characters")
        String homeAddress,

        @NotNull(message = "active is required")
        Boolean active,

        UUID createdBy,

        UUID updatedBy,

        @NotNull(message = "municipalityId is required")
        UUID municipalityId
) {
}
