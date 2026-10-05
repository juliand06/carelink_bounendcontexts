package com.carelink.domain.drugadministrationroute.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public record DrugAdministrationRouteUpdatedEvent(
        DrugAdministrationRouteId id,
        String code,
        String label,
        Boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DrugAdministrationRouteUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(label, "label must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
