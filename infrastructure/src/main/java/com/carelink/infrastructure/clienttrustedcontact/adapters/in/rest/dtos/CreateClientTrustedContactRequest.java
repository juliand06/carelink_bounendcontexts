package com.carelink.infrastructure.clienttrustedcontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateClientTrustedContactRequest(
        @NotNull(message = "clientId is required")
        UUID clientId,

        @NotNull(message = "trustedContactId is required")
        UUID trustedContactId
) {
}
