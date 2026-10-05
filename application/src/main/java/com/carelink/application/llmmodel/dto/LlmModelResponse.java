package com.carelink.application.llmmodel.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record LlmModelResponse(
        UUID id,
        UUID llmVendorId,
        String displayName,
        String apiIdentifier,
        BigDecimal pricePerInputToken,
        BigDecimal pricePerOutputToken,
        Integer maxOutputTokens,
        Integer contextLength,
        Boolean isEnabled,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
