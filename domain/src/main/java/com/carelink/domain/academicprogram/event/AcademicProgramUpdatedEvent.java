package com.carelink.domain.academicprogram.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.common.event.DomainEvent;

public record AcademicProgramUpdatedEvent(
        AcademicProgramId id,
        String title,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AcademicProgramUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
