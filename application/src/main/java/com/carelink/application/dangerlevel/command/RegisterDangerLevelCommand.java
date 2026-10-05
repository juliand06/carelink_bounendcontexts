package com.carelink.application.dangerlevel.command;

import java.util.Objects;

public record RegisterDangerLevelCommand(
        String code,
        String label,
        Boolean active,
        Integer severityRank
) {
    public RegisterDangerLevelCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(severityRank, "severityRank must not be null");
    }
}
