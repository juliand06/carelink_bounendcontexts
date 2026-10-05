package com.carelink.application.handoffassignment.usecase;

import com.carelink.application.handoffassignment.command.RegisterHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.domain.handoffassignment.model.aggregate.HandoffAssignment;

public class RegisterHandoffAssignmentUseCase {
    private final HandoffAssignmentRepository repository;

    public RegisterHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        this.repository = repository;
    }

    public HandoffAssignmentResponse execute(RegisterHandoffAssignmentCommand command) {
        HandoffAssignment aggregate = HandoffAssignment.register(
                command.handoffId(),
                command.specialistId());
        HandoffAssignment saved = repository.save(aggregate);
        return new HandoffAssignmentResponse(
                saved.id().value(),
                saved.handoffId().value(),
                saved.specialistId().value(),
                saved.assignedAt());
    }
}
