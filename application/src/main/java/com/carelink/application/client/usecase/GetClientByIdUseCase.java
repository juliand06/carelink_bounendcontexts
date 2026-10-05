package com.carelink.application.client.usecase;

import com.carelink.application.client.dto.ClientResponse;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.application.client.exception.ClientNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;

public class GetClientByIdUseCase {
    private final ClientRepository repository;

    public GetClientByIdUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientResponse execute(ClientId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundApplicationException(id.value().toString()));
        return new ClientResponse(
                aggregate.id().value(),
                aggregate.idDocumentTypeId().value(),
                aggregate.documentNumber(),
                aggregate.firstName(),
                aggregate.middleName(),
                aggregate.lastName(),
                aggregate.secondLastName(),
                aggregate.birthDate(),
                aggregate.biologicalSexId().value(),
                aggregate.genderIdentityId().value(),
                aggregate.email(),
                aggregate.mobilePhone(),
                aggregate.homeAddress(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.createdBy() == null ? null : aggregate.createdBy().value(),
                aggregate.updatedAt(),
                aggregate.updatedBy() == null ? null : aggregate.updatedBy().value(),
                aggregate.municipalityId().value());
    }
}
