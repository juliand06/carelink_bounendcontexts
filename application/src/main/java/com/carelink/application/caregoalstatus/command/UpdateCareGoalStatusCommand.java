package com.carelink.application.caregoalstatus.command;

import java.util.Objects;

import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;

public record UpdateCareGoalStatusCommand(
        CareGoalStatusId id,
        String code,
        String label,
        Boolean active
) {
    public UpdateCareGoalStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
    }
}
