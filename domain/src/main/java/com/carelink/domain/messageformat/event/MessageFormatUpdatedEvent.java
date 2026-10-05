package com.carelink.domain.messageformat.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public record MessageFormatUpdatedEvent(
        MessageFormatId id,
        String label,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MessageFormatUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
