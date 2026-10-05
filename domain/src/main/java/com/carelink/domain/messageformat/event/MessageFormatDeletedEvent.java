package com.carelink.domain.messageformat.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public record MessageFormatDeletedEvent(
        MessageFormatId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MessageFormatDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
