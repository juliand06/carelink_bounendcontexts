package com.carelink.application.senderrole.command;

import java.util.Objects;

import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public record UpdateSenderRoleCommand(
        SenderRoleId id,
        String label
) {
    public UpdateSenderRoleCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
    }
}
