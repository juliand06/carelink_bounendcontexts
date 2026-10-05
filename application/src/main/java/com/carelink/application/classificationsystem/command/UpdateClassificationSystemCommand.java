package com.carelink.application.classificationsystem.command;

import java.util.Objects;

import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;

public record UpdateClassificationSystemCommand(
        ClassificationSystemId id,
        String code,
        String label,
        Boolean active,
        String edition
) {
    public UpdateClassificationSystemCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
