package com.carelink.application.evaluationinstrumenttype.usecase;

import java.time.LocalDateTime;

import com.carelink.application.evaluationinstrumenttype.exception.EvaluationInstrumentTypeNotFoundApplicationException;
import com.carelink.domain.evaluationinstrumenttype.event.EvaluationInstrumentTypeDeletedEvent;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;

public class DeleteEvaluationInstrumentTypeUseCase {
    private final EvaluationInstrumentTypeRepository repository;

    public DeleteEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        this.repository = repository;
    }

    public EvaluationInstrumentTypeDeletedEvent execute(EvaluationInstrumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EvaluationInstrumentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EvaluationInstrumentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
