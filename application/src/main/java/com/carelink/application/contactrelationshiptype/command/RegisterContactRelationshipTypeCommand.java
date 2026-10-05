package com.carelink.application.contactrelationshiptype.command;

import java.util.Objects;

public record RegisterContactRelationshipTypeCommand(
        String label
) {
    public RegisterContactRelationshipTypeCommand {
        Objects.requireNonNull(label, "label must not be null");
    }
}
