package com.carelink.domain.drugadministrationroute.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public record DrugAdministrationRouteDeletedEvent(
        DrugAdministrationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DrugAdministrationRouteDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
