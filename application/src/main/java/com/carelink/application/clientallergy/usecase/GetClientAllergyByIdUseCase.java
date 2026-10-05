package com.carelink.application.clientallergy.usecase;

import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.application.clientallergy.exception.ClientAllergyNotFoundApplicationException;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;

public class GetClientAllergyByIdUseCase {
    private final ClientAllergyRepository repository;

    public GetClientAllergyByIdUseCase(ClientAllergyRepository repository) {
        this.repository = repository;
    }

    public ClientAllergyResponse execute(ClientAllergyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientAllergyNotFoundApplicationException(id.value().toString()));
        return new ClientAllergyResponse(
                aggregate.id().value(),
                aggregate.clientId().value(),
                aggregate.allergen(),
                aggregate.reactionDescription(),
                aggregate.severityLevel(),
                aggregate.active(),
                aggregate.registeredAt(),
                aggregate.registeredBy().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
