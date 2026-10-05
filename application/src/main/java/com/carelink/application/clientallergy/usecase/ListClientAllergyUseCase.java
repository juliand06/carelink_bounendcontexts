package com.carelink.application.clientallergy.usecase;

import java.util.List;

import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;

public class ListClientAllergyUseCase {
    private final ClientAllergyRepository repository;

    public ListClientAllergyUseCase(ClientAllergyRepository repository) {
        this.repository = repository;
    }

    public List<ClientAllergyResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClientAllergyResponse(
                        aggregate.id().value(),
                        aggregate.clientId().value(),
                        aggregate.allergen(),
                        aggregate.reactionDescription(),
                        aggregate.severityLevel(),
                        aggregate.active(),
                        aggregate.registeredAt(),
                        aggregate.registeredBy().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
