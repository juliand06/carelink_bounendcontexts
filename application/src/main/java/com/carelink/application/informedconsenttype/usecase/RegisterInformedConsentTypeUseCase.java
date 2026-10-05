package com.carelink.application.informedconsenttype.usecase;

import com.carelink.application.informedconsenttype.command.RegisterInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;

public class RegisterInformedConsentTypeUseCase {
    private final InformedConsentTypeRepository repository;

    public RegisterInformedConsentTypeUseCase(InformedConsentTypeRepository repository) {
        this.repository = repository;
    }

    public InformedConsentTypeResponse execute(RegisterInformedConsentTypeCommand command) {
        InformedConsentType aggregate = InformedConsentType.register(
                command.code(),
                command.label(),
                command.active(),
                command.description());
        InformedConsentType saved = repository.save(aggregate);
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
