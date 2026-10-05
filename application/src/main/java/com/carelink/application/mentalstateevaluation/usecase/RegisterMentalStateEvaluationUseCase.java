package com.carelink.application.mentalstateevaluation.usecase;

import com.carelink.application.mentalstateevaluation.command.RegisterMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;

public class RegisterMentalStateEvaluationUseCase {
    private final MentalStateEvaluationRepository repository;

    public RegisterMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        this.repository = repository;
    }

    public MentalStateEvaluationResponse execute(RegisterMentalStateEvaluationCommand command) {
        MentalStateEvaluation aggregate = MentalStateEvaluation.register(
                command.appointmentId(),
                command.generalAppearance(),
                command.behavioralObservation(),
                command.attitudeTowardExaminer(),
                command.levelOfConsciousness(),
                command.orientationStatus(),
                command.attentionAndConcentration(),
                command.memoryAssessment(),
                command.speechCharacteristics(),
                command.moodDescription(),
                command.affectDescription(),
                command.thoughtProcess(),
                command.thoughtContent(),
                command.perceptualDisturbances(),
                command.judgmentAssessment(),
                command.insightAssessment(),
                command.psychomotorFindings(),
                command.examinerRemarks(),
                command.createdBy());
        MentalStateEvaluation saved = repository.save(aggregate);
        return new MentalStateEvaluationResponse(
                saved.id().value(),
                saved.appointmentId().value(),
                saved.generalAppearance(),
                saved.behavioralObservation(),
                saved.attitudeTowardExaminer(),
                saved.levelOfConsciousness(),
                saved.orientationStatus(),
                saved.attentionAndConcentration(),
                saved.memoryAssessment(),
                saved.speechCharacteristics(),
                saved.moodDescription(),
                saved.affectDescription(),
                saved.thoughtProcess(),
                saved.thoughtContent(),
                saved.perceptualDisturbances(),
                saved.judgmentAssessment(),
                saved.insightAssessment(),
                saved.psychomotorFindings(),
                saved.examinerRemarks(),
                saved.createdAt(),
                saved.createdBy().value());
    }
}
