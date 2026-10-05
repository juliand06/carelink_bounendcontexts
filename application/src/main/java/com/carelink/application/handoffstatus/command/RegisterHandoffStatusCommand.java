package com.carelink.application.handoffstatus.command;

import java.util.Objects;

public record RegisterHandoffStatusCommand(
        String label
) {
    public RegisterHandoffStatusCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
