package com.carelink.application.supportconversationstatus.command;

import java.util.Objects;

import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public record UpdateSupportConversationStatusCommand(
        SupportConversationStatusId id,
        String code,
        String label
) {
    public UpdateSupportConversationStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
