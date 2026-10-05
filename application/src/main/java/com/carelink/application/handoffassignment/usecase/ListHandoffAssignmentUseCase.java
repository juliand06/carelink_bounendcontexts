package com.carelink.application.handoffassignment.usecase;

import java.util.List;

import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;

public class ListHandoffAssignmentUseCase {
    private final HandoffAssignmentRepository repository;

    public ListHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        this.repository = repository;
    }

    public List<HandoffAssignmentResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new HandoffAssignmentResponse(
                        aggregate.id().value(),
                        aggregate.handoffId().value(),
                        aggregate.specialistId().value(),
                        aggregate.assignedAt()))
                .toList();
    }
}
