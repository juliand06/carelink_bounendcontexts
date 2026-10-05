package com.carelink.domain.supportconversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public record SupportConversationStatusRegisteredEvent(
        SupportConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportConversationStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
