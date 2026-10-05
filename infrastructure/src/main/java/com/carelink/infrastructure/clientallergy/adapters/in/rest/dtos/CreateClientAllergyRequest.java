package com.carelink.infrastructure.clientallergy.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateClientAllergyRequest(
        @NotNull(message = "clientId is required")
        UUID clientId,

        @NotNull(message = "allergen is required")
        @Size(max = 200, message = "allergen must have at most 200 characters")
        String allergen,

        String reactionDescription,

        @Size(max = 20, message = "severityLevel must have at most 20 characters")
        String severityLevel,

        @NotNull(message = "active is required")
        Boolean active,

        @NotNull(message = "registeredBy is required")
        UUID registeredBy
) {
}
