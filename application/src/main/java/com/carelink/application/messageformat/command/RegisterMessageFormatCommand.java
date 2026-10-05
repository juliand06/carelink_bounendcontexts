package com.carelink.application.messageformat.command;

import java.util.Objects;

public record RegisterMessageFormatCommand(
        String label
) {
    public RegisterMessageFormatCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
