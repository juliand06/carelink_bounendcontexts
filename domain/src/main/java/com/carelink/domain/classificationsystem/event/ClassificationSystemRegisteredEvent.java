package com.carelink.domain.classificationsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.common.event.DomainEvent;

public record ClassificationSystemRegisteredEvent(
        ClassificationSystemId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClassificationSystemRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
