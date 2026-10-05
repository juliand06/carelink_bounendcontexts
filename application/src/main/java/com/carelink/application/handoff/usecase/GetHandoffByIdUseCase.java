package com.carelink.application.handoff.usecase;

import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.application.handoff.exception.HandoffNotFoundApplicationException;
import com.carelink.domain.handoff.model.valueobject.HandoffId;

public class GetHandoffByIdUseCase {
    private final HandoffRepository repository;

    public GetHandoffByIdUseCase(HandoffRepository repository) {
        this.repository = repository;
    }

    public HandoffResponse execute(HandoffId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffNotFoundApplicationException(id.value().toString()));
        return new HandoffResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.handoffStatusId().value(),
                aggregate.originatedFromAi(),
                aggregate.handoffReason(),
                aggregate.createdAt());
    }
}
