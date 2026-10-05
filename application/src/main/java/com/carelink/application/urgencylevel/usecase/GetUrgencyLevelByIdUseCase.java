package com.carelink.application.urgencylevel.usecase;

import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import com.carelink.application.urgencylevel.exception.UrgencyLevelNotFoundApplicationException;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public class GetUrgencyLevelByIdUseCase {
    private final UrgencyLevelRepository repository;

    public GetUrgencyLevelByIdUseCase(UrgencyLevelRepository repository) {
        this.repository = repository;
    }

    public UrgencyLevelResponse execute(UrgencyLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new UrgencyLevelNotFoundApplicationException(id.value().toString()));
        return new UrgencyLevelResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
