package com.carelink.application.handoffassignment.usecase;

import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;
import com.carelink.application.handoffassignment.exception.HandoffAssignmentNotFoundApplicationException;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;

public class GetHandoffAssignmentByIdUseCase {
    private final HandoffAssignmentRepository repository;

    public GetHandoffAssignmentByIdUseCase(HandoffAssignmentRepository repository) {
        this.repository = repository;
    }

    public HandoffAssignmentResponse execute(HandoffAssignmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffAssignmentNotFoundApplicationException(id.value().toString()));
        return new HandoffAssignmentResponse(
                aggregate.id().value(),
                aggregate.handoffId().value(),
                aggregate.specialistId().value(),
                aggregate.assignedAt());
    }
}
