package com.carelink.domain.clientallergy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record ClientAllergyUpdatedEvent(
        ClientAllergyId id,
        ClientId clientId,
        String allergen,
        String reactionDescription,
        String severityLevel,
        Boolean active,
        SpecialistId registeredBy,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClientAllergyUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(allergen, "allergen must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(registeredBy, "registeredBy must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
