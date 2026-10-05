package com.carelink.application.mentalstateevaluation.usecase;

import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.application.mentalstateevaluation.exception.MentalStateEvaluationNotFoundApplicationException;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;

public class GetMentalStateEvaluationByIdUseCase {
    private final MentalStateEvaluationRepository repository;

    public GetMentalStateEvaluationByIdUseCase(MentalStateEvaluationRepository repository) {
        this.repository = repository;
    }

    public MentalStateEvaluationResponse execute(MentalStateEvaluationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MentalStateEvaluationNotFoundApplicationException(id.value().toString()));
        return new MentalStateEvaluationResponse(
                aggregate.id().value(),
                aggregate.appointmentId().value(),
                aggregate.generalAppearance(),
                aggregate.behavioralObservation(),
                aggregate.attitudeTowardExaminer(),
                aggregate.levelOfConsciousness(),
                aggregate.orientationStatus(),
                aggregate.attentionAndConcentration(),
                aggregate.memoryAssessment(),
                aggregate.speechCharacteristics(),
                aggregate.moodDescription(),
                aggregate.affectDescription(),
                aggregate.thoughtProcess(),
                aggregate.thoughtContent(),
                aggregate.perceptualDisturbances(),
                aggregate.judgmentAssessment(),
                aggregate.insightAssessment(),
                aggregate.psychomotorFindings(),
                aggregate.examinerRemarks(),
                aggregate.createdAt(),
                aggregate.createdBy().value());
    }
}
