package com.carelink.application.client.usecase;

import com.carelink.application.client.command.RegisterClientCommand;
import com.carelink.application.client.dto.ClientResponse;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.domain.client.model.aggregate.Client;

public class RegisterClientUseCase {
    private final ClientRepository repository;

    public RegisterClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientResponse execute(RegisterClientCommand command) {
        Client aggregate = Client.register(
                command.idDocumentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentityId(),
                command.email(),
                command.mobilePhone(),
                command.homeAddress(),
                command.active(),
                command.createdBy(),
                command.updatedBy(),
                command.municipalityId());
        Client saved = repository.save(aggregate);
        return new ClientResponse(
                saved.id().value(),
                saved.idDocumentTypeId().value(),
                saved.documentNumber(),
                saved.firstName(),
                saved.middleName(),
                saved.lastName(),
                saved.secondLastName(),
                saved.birthDate(),
                saved.biologicalSexId().value(),
                saved.genderIdentityId().value(),
                saved.email(),
                saved.mobilePhone(),
                saved.homeAddress(),
                saved.active(),
                saved.createdAt(),
                saved.createdBy() == null ? null : saved.createdBy().value(),
                saved.updatedAt(),
                saved.updatedBy() == null ? null : saved.updatedBy().value(),
                saved.municipalityId().value());
    }
}
