package com.carelink.application.dangerassessment.usecase;

import com.carelink.application.dangerassessment.command.UpdateDangerAssessmentCommand;
import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.application.dangerassessment.exception.DangerAssessmentNotFoundApplicationException;

public class UpdateDangerAssessmentUseCase {
    private final DangerAssessmentRepository repository;

    public UpdateDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        this.repository = repository;
    }

    public DangerAssessmentResponse execute(UpdateDangerAssessmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DangerAssessmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.appointmentId(),
                command.dangerLevelId(),
                command.suicidalIdeation(),
                command.hasSuicidePlan(),
                command.hasSuicideIntent(),
                command.selfHarmBehavior(),
                command.riskToOthers(),
                command.protectiveFactors(),
                command.identifiedRiskFactors(),
                command.clinicalActionsTaken(),
                command.evaluatorObservations(),
                command.evaluatedBy());
        var saved = repository.save(aggregate);
        return new DangerAssessmentResponse(
                saved.id().value(),
                saved.appointmentId().value(),
                saved.dangerLevelId().value(),
                saved.suicidalIdeation(),
                saved.hasSuicidePlan(),
                saved.hasSuicideIntent(),
                saved.selfHarmBehavior(),
                saved.riskToOthers(),
                saved.protectiveFactors(),
                saved.identifiedRiskFactors(),
                saved.clinicalActionsTaken(),
                saved.evaluatorObservations(),
                saved.evaluatedAt(),
                saved.evaluatedBy().value());
    }
}
