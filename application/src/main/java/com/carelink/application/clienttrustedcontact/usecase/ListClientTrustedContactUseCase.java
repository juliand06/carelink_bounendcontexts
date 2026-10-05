package com.carelink.application.clienttrustedcontact.usecase;

import java.util.List;

import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;

public class ListClientTrustedContactUseCase {
    private final ClientTrustedContactRepository repository;

    public ListClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        this.repository = repository;
    }

    public List<ClientTrustedContactResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClientTrustedContactResponse(
                        aggregate.id().value(),
                        aggregate.clientId().value(),
                        aggregate.trustedContactId().value(),
                        aggregate.createdAt()))
                .toList();
    }
}
