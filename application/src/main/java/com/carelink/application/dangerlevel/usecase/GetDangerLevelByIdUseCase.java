package com.carelink.application.dangerlevel.usecase;

import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import com.carelink.application.dangerlevel.exception.DangerLevelNotFoundApplicationException;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public class GetDangerLevelByIdUseCase {
    private final DangerLevelRepository repository;

    public GetDangerLevelByIdUseCase(DangerLevelRepository repository) {
        this.repository = repository;
    }

    public DangerLevelResponse execute(DangerLevelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DangerLevelNotFoundApplicationException(id.value().toString()));
        return new DangerLevelResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.severityRank(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
