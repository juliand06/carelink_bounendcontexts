package com.carelink.domain.senderrole.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public record SenderRoleDeletedEvent(
        SenderRoleId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SenderRoleDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
