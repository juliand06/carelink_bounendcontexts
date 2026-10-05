package com.carelink.domain.country.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.country.model.valueobject.CountryId;

public record CountryUpdatedEvent(
        CountryId id,
        String name,
        String isoCode,
        String notes,
        Boolean enabled,
        String dialPrefix,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CountryUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(isoCode, "isoCode must not be null");
        Objects.requireNonNull(enabled, "enabled must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
