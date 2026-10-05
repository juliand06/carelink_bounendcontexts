package com.carelink.application.dangerlevel.command;

import java.util.Objects;

import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public record UpdateDangerLevelCommand(
        DangerLevelId id,
        String code,
        String label,
        Boolean active,
        Integer severityRank
) {
    public UpdateDangerLevelCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(severityRank, "severityRank must not be null");
    }
}
