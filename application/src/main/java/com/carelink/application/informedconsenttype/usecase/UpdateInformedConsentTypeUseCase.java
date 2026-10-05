package com.carelink.application.informedconsenttype.usecase;

import com.carelink.application.informedconsenttype.command.UpdateInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import com.carelink.application.informedconsenttype.exception.InformedConsentTypeNotFoundApplicationException;

public class UpdateInformedConsentTypeUseCase {
    private final InformedConsentTypeRepository repository;

    public UpdateInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        this.repository = repository;
    }

    public InformedConsentTypeResponse execute(UpdateInformedConsentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new InformedConsentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active(),
                command.description());
        var saved = repository.save(aggregate);
        return new InformedConsentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
