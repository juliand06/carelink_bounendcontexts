package com.carelink.application.dangerassessment.usecase;

import java.util.List;

import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;

public class ListDangerAssessmentUseCase {
    private final DangerAssessmentRepository repository;

    public ListDangerAssessmentUseCase(DangerAssessmentRepository repository) {
        this.repository = repository;
    }

    public List<DangerAssessmentResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new DangerAssessmentResponse(
                        aggregate.id().value(),
                        aggregate.appointmentId().value(),
                        aggregate.dangerLevelId().value(),
                        aggregate.suicidalIdeation(),
                        aggregate.hasSuicidePlan(),
                        aggregate.hasSuicideIntent(),
                        aggregate.selfHarmBehavior(),
                        aggregate.riskToOthers(),
                        aggregate.protectiveFactors(),
                        aggregate.identifiedRiskFactors(),
                        aggregate.clinicalActionsTaken(),
                        aggregate.evaluatorObservations(),
                        aggregate.evaluatedAt(),
                        aggregate.evaluatedBy().value()))
                .toList();
    }
}
