package com.carelink.application.healthrecordstatus.command;

import java.util.Objects;

public record RegisterHealthRecordStatusCommand(
        String code,
        String label
) {
    public RegisterHealthRecordStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
