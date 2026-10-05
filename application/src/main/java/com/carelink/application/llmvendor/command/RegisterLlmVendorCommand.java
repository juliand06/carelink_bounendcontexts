package com.carelink.application.llmvendor.command;

import java.util.Objects;

public record RegisterLlmVendorCommand(
        String brandName,
        String legalName,
        String websiteUrl,
        Boolean isEnabled
) {
    public RegisterLlmVendorCommand {
        Objects.requireNonNull(brandName, "brandName must not be null");
        Objects.requireNonNull(isEnabled, "isEnabled must not be null");
    }
}
