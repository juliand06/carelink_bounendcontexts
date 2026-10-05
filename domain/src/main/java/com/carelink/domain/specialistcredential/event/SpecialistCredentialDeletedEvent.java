package com.carelink.domain.specialistcredential.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public record SpecialistCredentialDeletedEvent(
        SpecialistCredentialId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SpecialistCredentialDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
