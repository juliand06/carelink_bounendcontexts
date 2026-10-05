package com.carelink.domain.classificationsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.common.event.DomainEvent;

public record ClassificationSystemUpdatedEvent(
        ClassificationSystemId id,
        String code,
        String label,
        Boolean active,
        String edition,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClassificationSystemUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
