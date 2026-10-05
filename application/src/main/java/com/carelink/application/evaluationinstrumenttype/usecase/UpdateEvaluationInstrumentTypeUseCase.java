package com.carelink.application.evaluationinstrumenttype.usecase;

import com.carelink.application.evaluationinstrumenttype.command.UpdateEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import com.carelink.application.evaluationinstrumenttype.exception.EvaluationInstrumentTypeNotFoundApplicationException;

public class UpdateEvaluationInstrumentTypeUseCase {
    private final EvaluationInstrumentTypeRepository repository;

    public UpdateEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        this.repository = repository;
    }

    public EvaluationInstrumentTypeResponse execute(UpdateEvaluationInstrumentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EvaluationInstrumentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active(),
                command.description());
        var saved = repository.save(aggregate);
        return new EvaluationInstrumentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
