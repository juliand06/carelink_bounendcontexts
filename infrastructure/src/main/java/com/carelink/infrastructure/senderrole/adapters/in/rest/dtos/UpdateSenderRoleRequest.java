package com.carelink.infrastructure.senderrole.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateSenderRoleRequest(
        @NotNull(message = "label is required")
        @Size(max = 50, message = "label must have at most 50 characters")
        String label
) {
}
