package com.carelink.application.informedconsenttype.usecase;

import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import com.carelink.application.informedconsenttype.exception.InformedConsentTypeNotFoundApplicationException;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;

public class GetInformedConsentTypeByIdUseCase {
    private final InformedConsentTypeRepository repository;

    public GetInformedConsentTypeByIdUseCase(InformedConsentTypeRepository repository) {
        this.repository = repository;
    }

    public InformedConsentTypeResponse execute(InformedConsentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new InformedConsentTypeNotFoundApplicationException(id.value().toString()));
        return new InformedConsentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
