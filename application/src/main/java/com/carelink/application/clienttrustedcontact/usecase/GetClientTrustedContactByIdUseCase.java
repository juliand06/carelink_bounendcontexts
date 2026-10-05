package com.carelink.application.clienttrustedcontact.usecase;

import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.application.clienttrustedcontact.exception.ClientTrustedContactNotFoundApplicationException;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;

public class GetClientTrustedContactByIdUseCase {
    private final ClientTrustedContactRepository repository;

    public GetClientTrustedContactByIdUseCase(ClientTrustedContactRepository repository) {
        this.repository = repository;
    }

    public ClientTrustedContactResponse execute(ClientTrustedContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientTrustedContactNotFoundApplicationException(id.value().toString()));
        return new ClientTrustedContactResponse(
                aggregate.id().value(),
                aggregate.clientId().value(),
                aggregate.trustedContactId().value(),
                aggregate.createdAt());
    }
}
