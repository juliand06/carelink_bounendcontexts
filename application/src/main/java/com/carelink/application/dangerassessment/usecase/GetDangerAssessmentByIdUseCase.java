package com.carelink.application.dangerassessment.usecase;

import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.application.dangerassessment.exception.DangerAssessmentNotFoundApplicationException;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;

public class GetDangerAssessmentByIdUseCase {
    private final DangerAssessmentRepository repository;

    public GetDangerAssessmentByIdUseCase(DangerAssessmentRepository repository) {
        this.repository = repository;
    }

    public DangerAssessmentResponse execute(DangerAssessmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DangerAssessmentNotFoundApplicationException(id.value().toString()));
        return new DangerAssessmentResponse(
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
                aggregate.evaluatedBy().value());
    }
}
