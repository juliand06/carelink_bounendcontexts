package com.carelink.application.senderrole.command;

import java.util.Objects;

public record RegisterSenderRoleCommand(
        String label
) {
    public RegisterSenderRoleCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
