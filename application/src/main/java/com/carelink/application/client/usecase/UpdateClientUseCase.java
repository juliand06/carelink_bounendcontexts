package com.carelink.application.client.usecase;

import com.carelink.application.client.command.UpdateClientCommand;
import com.carelink.application.client.dto.ClientResponse;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.application.client.exception.ClientNotFoundApplicationException;

public class UpdateClientUseCase {
    private final ClientRepository repository;

    public UpdateClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientResponse execute(UpdateClientCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClientNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
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
                command.updatedBy(),
                command.municipalityId());
        var saved = repository.save(aggregate);
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
