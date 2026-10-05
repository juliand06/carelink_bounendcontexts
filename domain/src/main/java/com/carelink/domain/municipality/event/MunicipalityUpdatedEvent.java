package com.carelink.domain.municipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public record MunicipalityUpdatedEvent(
        MunicipalityId id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        DepartmentId departmentId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MunicipalityUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
        Objects.requireNonNull(departmentId, "departmentId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
