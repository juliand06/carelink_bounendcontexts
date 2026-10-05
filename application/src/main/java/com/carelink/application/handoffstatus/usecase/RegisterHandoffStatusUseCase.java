package com.carelink.application.handoffstatus.usecase;

import com.carelink.application.handoffstatus.command.RegisterHandoffStatusCommand;
import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;

public class RegisterHandoffStatusUseCase {
    private final HandoffStatusRepository repository;

    public RegisterHandoffStatusUseCase(HandoffStatusRepository repository) {
        this.repository = repository;
    }

    public HandoffStatusResponse execute(RegisterHandoffStatusCommand command) {
        HandoffStatus aggregate = HandoffStatus.register(
                command.label());
        HandoffStatus saved = repository.save(aggregate);
        return new HandoffStatusResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
