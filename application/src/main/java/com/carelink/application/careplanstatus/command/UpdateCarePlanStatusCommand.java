package com.carelink.application.careplanstatus.command;

import java.util.Objects;

import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;

public record UpdateCarePlanStatusCommand(
        CarePlanStatusId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateCarePlanStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
