package com.carelink.domain.municipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public record MunicipalityDeletedEvent(
        MunicipalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MunicipalityDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
