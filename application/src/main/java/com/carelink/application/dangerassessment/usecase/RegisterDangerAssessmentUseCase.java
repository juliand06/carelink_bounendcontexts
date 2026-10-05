package com.carelink.application.dangerassessment.usecase;

import com.carelink.application.dangerassessment.command.RegisterDangerAssessmentCommand;
import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;

public class RegisterDangerAssessmentUseCase {
    private final DangerAssessmentRepository repository;

    public RegisterDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        this.repository = repository;
    }

    public DangerAssessmentResponse execute(RegisterDangerAssessmentCommand command) {
        DangerAssessment aggregate = DangerAssessment.register(
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
        DangerAssessment saved = repository.save(aggregate);
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
