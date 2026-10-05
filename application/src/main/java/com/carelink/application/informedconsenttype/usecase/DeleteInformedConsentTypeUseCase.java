package com.carelink.application.informedconsenttype.usecase;

import java.time.LocalDateTime;

import com.carelink.application.informedconsenttype.exception.InformedConsentTypeNotFoundApplicationException;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeDeletedEvent;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;

public class DeleteInformedConsentTypeUseCase {
    private final InformedConsentTypeRepository repository;

    public DeleteInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        this.repository = repository;
    }

    public InformedConsentTypeDeletedEvent execute(InformedConsentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new InformedConsentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new InformedConsentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
