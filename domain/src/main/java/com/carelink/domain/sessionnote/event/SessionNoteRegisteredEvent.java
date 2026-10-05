package com.carelink.domain.sessionnote.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;

public record SessionNoteRegisteredEvent(
        SessionNoteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SessionNoteRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
