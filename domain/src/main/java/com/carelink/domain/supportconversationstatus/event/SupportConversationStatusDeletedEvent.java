package com.carelink.domain.supportconversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public record SupportConversationStatusDeletedEvent(
        SupportConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SupportConversationStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
