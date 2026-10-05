package com.carelink.application.dangerassessment.usecase;

import java.time.LocalDateTime;

import com.carelink.application.dangerassessment.exception.DangerAssessmentNotFoundApplicationException;
import com.carelink.domain.dangerassessment.event.DangerAssessmentDeletedEvent;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;

public class DeleteDangerAssessmentUseCase {
    private final DangerAssessmentRepository repository;

    public DeleteDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        this.repository = repository;
    }

    public DangerAssessmentDeletedEvent execute(DangerAssessmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DangerAssessmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DangerAssessmentDeletedEvent(id, LocalDateTime.now());
    }
}
