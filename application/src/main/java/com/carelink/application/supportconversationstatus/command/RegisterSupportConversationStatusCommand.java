package com.carelink.application.supportconversationstatus.command;

import java.util.Objects;

public record RegisterSupportConversationStatusCommand(
        String code,
        String label
) {
    public RegisterSupportConversationStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
