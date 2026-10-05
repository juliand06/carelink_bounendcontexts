package com.carelink.application.llmvendor.command;

import java.util.Objects;

import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;

public record UpdateLlmVendorCommand(
        LlmVendorId id,
        String brandName,
        String legalName,
        String websiteUrl,
        Boolean isEnabled
) {
    public UpdateLlmVendorCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(brandName, "brandName must not be null");
        Objects.requireNonNull(isEnabled, "isEnabled must not be null");
    }
}
