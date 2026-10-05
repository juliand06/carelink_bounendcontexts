package com.carelink.application.informedconsenttype.command;

import java.util.Objects;

import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public record UpdateInformedConsentTypeCommand(
        InformedConsentTypeId id,
        String code,
        String label,
        Boolean active,
        String description
) {
    public UpdateInformedConsentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
