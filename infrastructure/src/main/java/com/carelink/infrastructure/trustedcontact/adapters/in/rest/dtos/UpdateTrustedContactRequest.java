package com.carelink.infrastructure.trustedcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateTrustedContactRequest(
        @NotNull(message = "fullName is required")
        @Size(max = 150, message = "fullName must have at most 150 characters")
        String fullName,

        @NotNull(message = "relationshipTypeId is required")
        UUID relationshipTypeId,

        @NotNull(message = "isPrimaryContact is required")
        Boolean isPrimaryContact,

        String notes
) {
}
