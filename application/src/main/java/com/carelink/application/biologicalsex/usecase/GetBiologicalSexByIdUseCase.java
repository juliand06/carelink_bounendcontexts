package com.carelink.application.biologicalsex.usecase;

import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import com.carelink.application.biologicalsex.exception.BiologicalSexNotFoundApplicationException;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;

public class GetBiologicalSexByIdUseCase {
    private final BiologicalSexRepository repository;

    public GetBiologicalSexByIdUseCase(BiologicalSexRepository repository) {
        this.repository = repository;
    }

    public BiologicalSexResponse execute(BiologicalSexId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new BiologicalSexNotFoundApplicationException(id.value().toString()));
        return new BiologicalSexResponse(
                aggregate.id().value(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
