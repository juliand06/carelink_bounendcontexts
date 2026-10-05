package com.carelink.application.iddocumenttype.usecase;

import com.carelink.application.iddocumenttype.command.UpdateIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import com.carelink.application.iddocumenttype.exception.IdDocumentTypeNotFoundApplicationException;

public class UpdateIdDocumentTypeUseCase {
    private final IdDocumentTypeRepository repository;

    public UpdateIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        this.repository = repository;
    }

    public IdDocumentTypeResponse execute(UpdateIdDocumentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new IdDocumentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new IdDocumentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
