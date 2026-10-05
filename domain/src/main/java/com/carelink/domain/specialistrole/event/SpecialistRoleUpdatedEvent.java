package com.carelink.domain.specialistrole.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public record SpecialistRoleUpdatedEvent(
        SpecialistRoleId id,
        String title,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SpecialistRoleUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
