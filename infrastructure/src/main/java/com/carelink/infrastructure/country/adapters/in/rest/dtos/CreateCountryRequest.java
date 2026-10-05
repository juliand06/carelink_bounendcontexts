package com.carelink.infrastructure.country.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCountryRequest(
        @NotNull(message = "name is required")
        @Size(max = 50, message = "name must have at most 50 characters")
        String name,

        @NotNull(message = "isoCode is required")
        @Size(max = 10, message = "isoCode must have at most 10 characters")
        String isoCode,

        @Size(max = 100, message = "notes must have at most 100 characters")
        String notes,

        @NotNull(message = "enabled is required")
        Boolean enabled,

        @Size(max = 5, message = "dialPrefix must have at most 5 characters")
        String dialPrefix
) {
}
