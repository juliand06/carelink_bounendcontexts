package com.carelink.infrastructure.messageformat.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateMessageFormatRequest(
        @NotNull(message = "label is required")
        @Size(max = 100, message = "label must have at most 100 characters")
        String label
) {
}
