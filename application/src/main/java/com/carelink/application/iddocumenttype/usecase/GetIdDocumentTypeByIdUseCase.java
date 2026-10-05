package com.carelink.application.iddocumenttype.usecase;

import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import com.carelink.application.iddocumenttype.exception.IdDocumentTypeNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;

public class GetIdDocumentTypeByIdUseCase {
    private final IdDocumentTypeRepository repository;

    public GetIdDocumentTypeByIdUseCase(IdDocumentTypeRepository repository) {
        this.repository = repository;
    }

    public IdDocumentTypeResponse execute(IdDocumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new IdDocumentTypeNotFoundApplicationException(id.value().toString()));
        return new IdDocumentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
