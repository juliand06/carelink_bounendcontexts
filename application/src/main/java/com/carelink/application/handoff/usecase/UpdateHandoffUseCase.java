package com.carelink.application.handoff.usecase;

import com.carelink.application.handoff.command.UpdateHandoffCommand;
import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.application.handoff.exception.HandoffNotFoundApplicationException;

public class UpdateHandoffUseCase {
    private final HandoffRepository repository;

    public UpdateHandoffUseCase(HandoffRepository repository) {
        this.repository = repository;
    }

    public HandoffResponse execute(UpdateHandoffCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HandoffNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.handoffStatusId(),
                command.originatedFromAi(),
                command.handoffReason());
        var saved = repository.save(aggregate);
        return new HandoffResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.handoffStatusId().value(),
                saved.originatedFromAi(),
                saved.handoffReason(),
                saved.createdAt());
    }
}
