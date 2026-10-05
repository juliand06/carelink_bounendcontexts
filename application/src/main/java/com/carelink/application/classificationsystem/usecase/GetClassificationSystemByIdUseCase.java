package com.carelink.application.classificationsystem.usecase;

import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import com.carelink.application.classificationsystem.exception.ClassificationSystemNotFoundApplicationException;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;

public class GetClassificationSystemByIdUseCase {
    private final ClassificationSystemRepository repository;

    public GetClassificationSystemByIdUseCase(ClassificationSystemRepository repository) {
        this.repository = repository;
    }

    public ClassificationSystemResponse execute(ClassificationSystemId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClassificationSystemNotFoundApplicationException(id.value().toString()));
        return new ClassificationSystemResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.edition(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
