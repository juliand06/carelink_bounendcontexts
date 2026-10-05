package com.carelink.application.clientallergy.usecase;

import java.time.LocalDateTime;

import com.carelink.application.clientallergy.exception.ClientAllergyNotFoundApplicationException;
import com.carelink.domain.clientallergy.event.ClientAllergyDeletedEvent;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;

public class DeleteClientAllergyUseCase {
    private final ClientAllergyRepository repository;

    public DeleteClientAllergyUseCase(ClientAllergyRepository repository) {
        this.repository = repository;
    }

    public ClientAllergyDeletedEvent execute(ClientAllergyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClientAllergyNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClientAllergyDeletedEvent(id, LocalDateTime.now());
    }
}
