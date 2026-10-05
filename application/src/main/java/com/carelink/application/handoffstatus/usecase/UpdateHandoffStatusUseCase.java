package com.carelink.application.handoffstatus.usecase;

import com.carelink.application.handoffstatus.command.UpdateHandoffStatusCommand;
import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import com.carelink.application.handoffstatus.exception.HandoffStatusNotFoundApplicationException;

public class UpdateHandoffStatusUseCase {
    private final HandoffStatusRepository repository;

    public UpdateHandoffStatusUseCase(HandoffStatusRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusResponse execute(UpdateHandoffStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HandoffStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new HandoffStatusResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
