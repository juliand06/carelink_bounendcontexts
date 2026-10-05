package com.carelink.application.urgencylevel.command;

import java.util.Objects;

import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public record UpdateUrgencyLevelCommand(
        UrgencyLevelId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateUrgencyLevelCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
