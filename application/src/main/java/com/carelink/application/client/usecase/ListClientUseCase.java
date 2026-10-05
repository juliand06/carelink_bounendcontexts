package com.carelink.application.client.usecase;

import java.util.List;

import com.carelink.application.client.dto.ClientResponse;
import com.carelink.domain.client.port.repository.ClientRepository;

public class ListClientUseCase {
    private final ClientRepository repository;

    public ListClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public List<ClientResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClientResponse(
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
                        aggregate.municipalityId().value()))
                .toList();
    }
}
