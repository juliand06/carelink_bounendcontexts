package com.carelink.application.iddocumenttype.usecase;

import java.time.LocalDateTime;

import com.carelink.application.iddocumenttype.exception.IdDocumentTypeNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeDeletedEvent;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;

public class DeleteIdDocumentTypeUseCase {
    private final IdDocumentTypeRepository repository;

    public DeleteIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        this.repository = repository;
    }

    public IdDocumentTypeDeletedEvent execute(IdDocumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new IdDocumentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new IdDocumentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
