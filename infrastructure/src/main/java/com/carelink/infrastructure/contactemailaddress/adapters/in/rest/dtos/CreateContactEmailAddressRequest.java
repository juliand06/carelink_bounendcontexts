package com.carelink.infrastructure.contactemailaddress.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateContactEmailAddressRequest(
        @NotNull(message = "trustedContactId is required")
        UUID trustedContactId,

        @NotNull(message = "emailAddress is required")
        @Size(max = 150, message = "emailAddress must have at most 150 characters")
        String emailAddress
) {
}
