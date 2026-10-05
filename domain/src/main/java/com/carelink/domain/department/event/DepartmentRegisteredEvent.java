package com.carelink.domain.department.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public record DepartmentRegisteredEvent(
        DepartmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DepartmentRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
