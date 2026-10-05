package com.carelink.infrastructure.contactphonenumber.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateContactPhoneNumberRequest(
        @NotNull(message = "trustedContactId is required")
        UUID trustedContactId,

        @NotNull(message = "phoneNumber is required")
        @Size(max = 30, message = "phoneNumber must have at most 30 characters")
        String phoneNumber
) {
}
