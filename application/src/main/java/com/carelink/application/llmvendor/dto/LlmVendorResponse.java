package com.carelink.application.llmvendor.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LlmVendorResponse(
        UUID id,
        String brandName,
        String legalName,
        String websiteUrl,
        Boolean isEnabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
