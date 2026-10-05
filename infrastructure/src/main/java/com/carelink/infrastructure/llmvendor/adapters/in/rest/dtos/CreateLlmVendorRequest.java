package com.carelink.infrastructure.llmvendor.adapters.in.rest.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateLlmVendorRequest(
        @NotNull(message = "brandName is required")
        @Size(max = 100, message = "brandName must have at most 100 characters")
        String brandName,

        @Size(max = 255, message = "legalName must have at most 255 characters")
        String legalName,

        String websiteUrl,

        @NotNull(message = "isEnabled is required")
        Boolean isEnabled
) {
}
