package com.carelink.application.handoffassignment.usecase;

import com.carelink.application.handoffassignment.command.UpdateHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.application.handoffassignment.exception.HandoffAssignmentNotFoundApplicationException;

public class UpdateHandoffAssignmentUseCase {
    private final HandoffAssignmentRepository repository;

    public UpdateHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        this.repository = repository;
    }

    public HandoffAssignmentResponse execute(UpdateHandoffAssignmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new HandoffAssignmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.handoffId(),
                command.specialistId());
        var saved = repository.save(aggregate);
        return new HandoffAssignmentResponse(
                saved.id().value(),
                saved.handoffId().value(),
                saved.specialistId().value(),
                saved.assignedAt());
    }
}
