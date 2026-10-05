package com.carelink.application.handoffstatus.command;

import java.util.Objects;

import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public record UpdateHandoffStatusCommand(
        HandoffStatusId id,
        String label
) {
    public UpdateHandoffStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
