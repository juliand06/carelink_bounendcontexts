package com.carelink.application.client.usecase;

import java.time.LocalDateTime;

import com.carelink.application.client.exception.ClientNotFoundApplicationException;
import com.carelink.domain.client.event.ClientDeletedEvent;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.client.port.repository.ClientRepository;

public class DeleteClientUseCase {
    private final ClientRepository repository;

    public DeleteClientUseCase(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientDeletedEvent execute(ClientId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClientDeletedEvent(id, LocalDateTime.now());
    }
}
