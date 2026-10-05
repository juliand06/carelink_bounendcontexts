package com.carelink.application.mentalstateevaluation.usecase;

import com.carelink.application.mentalstateevaluation.command.UpdateMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.application.mentalstateevaluation.exception.MentalStateEvaluationNotFoundApplicationException;

public class UpdateMentalStateEvaluationUseCase {
    private final MentalStateEvaluationRepository repository;

    public UpdateMentalStateEvaluationUseCase(MentalStateEvaluationRepository repository) {
        this.repository = repository;
    }

    public MentalStateEvaluationResponse execute(UpdateMentalStateEvaluationCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MentalStateEvaluationNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
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
                command.examinerRemarks());
        var saved = repository.save(aggregate);
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
