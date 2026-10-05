package com.carelink.application.evaluationinstrumenttype.usecase;

import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import com.carelink.application.evaluationinstrumenttype.exception.EvaluationInstrumentTypeNotFoundApplicationException;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;

public class GetEvaluationInstrumentTypeByIdUseCase {
    private final EvaluationInstrumentTypeRepository repository;

    public GetEvaluationInstrumentTypeByIdUseCase(EvaluationInstrumentTypeRepository repository) {
        this.repository = repository;
    }

    public EvaluationInstrumentTypeResponse execute(EvaluationInstrumentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EvaluationInstrumentTypeNotFoundApplicationException(id.value().toString()));
        return new EvaluationInstrumentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
