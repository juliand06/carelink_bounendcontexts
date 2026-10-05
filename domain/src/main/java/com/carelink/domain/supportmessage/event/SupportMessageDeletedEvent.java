package com.carelink.domain.supportmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public record SupportMessageDeletedEvent(
        SupportMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportMessageDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
