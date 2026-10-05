package com.carelink.application.handoff.usecase;

import com.carelink.application.handoff.command.RegisterHandoffCommand;
import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.domain.handoff.model.aggregate.Handoff;

public class RegisterHandoffUseCase {
    private final HandoffRepository repository;

    public RegisterHandoffUseCase(HandoffRepository repository) {
        this.repository = repository;
    }

    public HandoffResponse execute(RegisterHandoffCommand command) {
        Handoff aggregate = Handoff.register(
                command.conversationId(),
                command.handoffStatusId(),
                command.originatedFromAi(),
                command.handoffReason());
        Handoff saved = repository.save(aggregate);
        return new HandoffResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.handoffStatusId().value(),
                saved.originatedFromAi(),
                saved.handoffReason(),
                saved.createdAt());
    }
}
