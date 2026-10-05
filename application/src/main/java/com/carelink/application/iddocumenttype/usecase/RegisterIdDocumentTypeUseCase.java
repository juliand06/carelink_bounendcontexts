package com.carelink.application.iddocumenttype.usecase;

import com.carelink.application.iddocumenttype.command.RegisterIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;

public class RegisterIdDocumentTypeUseCase {
    private final IdDocumentTypeRepository repository;

    public RegisterIdDocumentTypeUseCase(IdDocumentTypeRepository repository) {
        this.repository = repository;
    }

    public IdDocumentTypeResponse execute(RegisterIdDocumentTypeCommand command) {
        IdDocumentType aggregate = IdDocumentType.register(
                command.code(),
                command.label(),
                command.active());
        IdDocumentType saved = repository.save(aggregate);
        return new IdDocumentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
