package com.carelink.application.evaluationinstrumenttype.usecase;

import com.carelink.application.evaluationinstrumenttype.command.RegisterEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;

public class RegisterEvaluationInstrumentTypeUseCase {
    private final EvaluationInstrumentTypeRepository repository;

    public RegisterEvaluationInstrumentTypeUseCase(EvaluationInstrumentTypeRepository repository) {
        this.repository = repository;
    }

    public EvaluationInstrumentTypeResponse execute(RegisterEvaluationInstrumentTypeCommand command) {
        EvaluationInstrumentType aggregate = EvaluationInstrumentType.register(
                command.code(),
                command.label(),
                command.active(),
                command.description());
        EvaluationInstrumentType saved = repository.save(aggregate);
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
