package com.carelink.application.clienttrustedcontact.usecase;

import java.time.LocalDateTime;

import com.carelink.application.clienttrustedcontact.exception.ClientTrustedContactNotFoundApplicationException;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactDeletedEvent;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;

public class DeleteClientTrustedContactUseCase {
    private final ClientTrustedContactRepository repository;

    public DeleteClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        this.repository = repository;
    }

    public ClientTrustedContactDeletedEvent execute(ClientTrustedContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientTrustedContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClientTrustedContactDeletedEvent(id, LocalDateTime.now());
    }
}
