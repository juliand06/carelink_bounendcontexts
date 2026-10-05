package com.carelink.application.mentalstateevaluation.usecase;

import java.time.LocalDateTime;

import com.carelink.application.mentalstateevaluation.exception.MentalStateEvaluationNotFoundApplicationException;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationDeletedEvent;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;

public class DeleteMentalStateEvaluationUseCase {
    private final MentalStateEvaluationRepository repository;

    public DeleteMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        this.repository = repository;
    }

    public MentalStateEvaluationDeletedEvent execute(MentalStateEvaluationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MentalStateEvaluationNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MentalStateEvaluationDeletedEvent(id, LocalDateTime.now());
    }
}
