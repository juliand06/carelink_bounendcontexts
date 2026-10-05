package com.carelink.application.mentalstateevaluation.usecase;

import java.util.List;

import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;

public class ListMentalStateEvaluationUseCase {
    private final MentalStateEvaluationRepository repository;

    public ListMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        this.repository = repository;
    }

    public List<MentalStateEvaluationResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new MentalStateEvaluationResponse(
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
                        aggregate.createdBy().value()))
                .toList();
    }
}
