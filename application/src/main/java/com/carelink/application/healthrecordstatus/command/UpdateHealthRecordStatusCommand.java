package com.carelink.application.healthrecordstatus.command;

import java.util.Objects;

import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public record UpdateHealthRecordStatusCommand(
        HealthRecordStatusId id,
        String code,
        String label
) {
    public UpdateHealthRecordStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
