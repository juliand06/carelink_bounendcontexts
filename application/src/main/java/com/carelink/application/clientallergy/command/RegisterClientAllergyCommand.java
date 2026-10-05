package com.carelink.application.clientallergy.command;

import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterClientAllergyCommand(
        ClientId clientId,
        String allergen,
        String reactionDescription,
        String severityLevel,
        Boolean active,
        SpecialistId registeredBy
) {
    public RegisterClientAllergyCommand {
        Objects.requireNonNull(clientId, "clientId must not be null");
        Objects.requireNonNull(allergen, "allergen must not be null");
        Objects.requireNonNull(active, "active must not be null");
        Objects.requireNonNull(registeredBy, "registeredBy must not be null");
    }
}
