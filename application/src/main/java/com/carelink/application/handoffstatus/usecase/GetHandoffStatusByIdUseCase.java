package com.carelink.application.handoffstatus.usecase;

import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import com.carelink.application.handoffstatus.exception.HandoffStatusNotFoundApplicationException;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public class GetHandoffStatusByIdUseCase {
    private final HandoffStatusRepository repository;

    public GetHandoffStatusByIdUseCase(HandoffStatusRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusResponse execute(HandoffStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffStatusNotFoundApplicationException(id.value().toString()));
        return new HandoffStatusResponse(
                aggregate.id().value(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
