package com.carelink.application.handoffassignment.usecase;

import java.time.LocalDateTime;

import com.carelink.application.handoffassignment.exception.HandoffAssignmentNotFoundApplicationException;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentDeletedEvent;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.handoffassignment.port.repository.HandoffAssignmentRepository;

public class DeleteHandoffAssignmentUseCase {
    private final HandoffAssignmentRepository repository;

    public DeleteHandoffAssignmentUseCase(HandoffAssignmentRepository repository) {
        this.repository = repository;
    }

    public HandoffAssignmentDeletedEvent execute(HandoffAssignmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new HandoffAssignmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new HandoffAssignmentDeletedEvent(id, LocalDateTime.now());
    }
}
