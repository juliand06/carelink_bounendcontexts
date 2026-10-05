package com.carelink.domain.senderrole.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SenderRoleId(UUID value) {
    public SenderRoleId {
        Objects.requireNonNull(value, "SenderRoleId value must not be null");
    }

    public static SenderRoleId generate() {
        return new SenderRoleId(UUID.randomUUID());
    }
}
