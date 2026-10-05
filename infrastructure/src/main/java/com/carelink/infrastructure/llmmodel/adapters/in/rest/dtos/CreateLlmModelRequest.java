package com.carelink.infrastructure.llmmodel.adapters.in.rest.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateLlmModelRequest(
        @NotNull(message = "llmVendorId is required")
        UUID llmVendorId,

        @NotNull(message = "displayName is required")
        @Size(max = 100, message = "displayName must have at most 100 characters")
        String displayName,

        @NotNull(message = "apiIdentifier is required")
        @Size(max = 120, message = "apiIdentifier must have at most 120 characters")
        String apiIdentifier,

        @NotNull(message = "pricePerInputToken is required")
        BigDecimal pricePerInputToken,

        @NotNull(message = "pricePerOutputToken is required")
        BigDecimal pricePerOutputToken,

        @NotNull(message = "maxOutputTokens is required")
        Integer maxOutputTokens,

        @NotNull(message = "contextLength is required")
        Integer contextLength,

        @NotNull(message = "isEnabled is required")
        Boolean isEnabled
) {
}
