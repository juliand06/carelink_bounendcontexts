package com.carelink.domain.department.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public record DepartmentDeletedEvent(
        DepartmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DepartmentDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
