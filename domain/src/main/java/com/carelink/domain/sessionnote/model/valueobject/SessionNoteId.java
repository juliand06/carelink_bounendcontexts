package com.carelink.domain.sessionnote.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record SessionNoteId(UUID value) {
    public SessionNoteId {
        Objects.requireNonNull(value, "SessionNoteId value must not be null");
    }

    public static SessionNoteId generate() {
        return new SessionNoteId(UUID.randomUUID());
    }
}
