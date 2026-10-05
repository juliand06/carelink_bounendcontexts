package com.carelink.application.clientallergy.usecase;

import com.carelink.application.clientallergy.command.UpdateClientAllergyCommand;
import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.application.clientallergy.exception.ClientAllergyNotFoundApplicationException;

public class UpdateClientAllergyUseCase {
    private final ClientAllergyRepository repository;

    public UpdateClientAllergyUseCase(ClientAllergyRepository repository) {
        this.repository = repository;
    }

    public ClientAllergyResponse execute(UpdateClientAllergyCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClientAllergyNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.clientId(),
                command.allergen(),
                command.reactionDescription(),
                command.severityLevel(),
                command.active(),
                command.registeredBy());
        var saved = repository.save(aggregate);
        return new ClientAllergyResponse(
                saved.id().value(),
                saved.clientId().value(),
                saved.allergen(),
                saved.reactionDescription(),
                saved.severityLevel(),
                saved.active(),
                saved.registeredAt(),
                saved.registeredBy().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
