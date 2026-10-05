package com.carelink.application.messageformat.command;

import java.util.Objects;

import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public record UpdateMessageFormatCommand(
        MessageFormatId id,
        String label
) {
    public UpdateMessageFormatCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
