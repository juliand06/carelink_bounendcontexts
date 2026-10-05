package com.carelink.application.biologicalsex.command;

import java.util.Objects;

public record RegisterBiologicalSexCommand(
        String label
) {
    public RegisterBiologicalSexCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
