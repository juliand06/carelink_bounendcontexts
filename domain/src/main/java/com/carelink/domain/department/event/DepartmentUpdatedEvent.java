package com.carelink.domain.department.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public record DepartmentUpdatedEvent(
        DepartmentId id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        CountryId countryId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DepartmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
