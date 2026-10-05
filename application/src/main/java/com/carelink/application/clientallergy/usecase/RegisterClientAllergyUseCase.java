package com.carelink.application.clientallergy.usecase;

import com.carelink.application.clientallergy.command.RegisterClientAllergyCommand;
import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;

public class RegisterClientAllergyUseCase {
    private final ClientAllergyRepository repository;

    public RegisterClientAllergyUseCase(ClientAllergyRepository repository) {
        this.repository = repository;
    }

    public ClientAllergyResponse execute(RegisterClientAllergyCommand command) {
        ClientAllergy aggregate = ClientAllergy.register(
                command.clientId(),
                command.allergen(),
                command.reactionDescription(),
                command.severityLevel(),
                command.active(),
                command.registeredBy());
        ClientAllergy saved = repository.save(aggregate);
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
