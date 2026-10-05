package com.carelink.application.evaluationinstrumenttype.usecase;

import java.util.List;

import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;

public class ListEvaluationInstrumentTypeUseCase {
    private final EvaluationInstrumentTypeRepository repository;

    public ListEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        this.repository = repository;
    }

    public List<EvaluationInstrumentTypeResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EvaluationInstrumentTypeResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.description(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
