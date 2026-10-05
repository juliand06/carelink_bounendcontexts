package com.carelink.application.mentalstateevaluation.command;

import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;

public record UpdateMentalStateEvaluationCommand(
        MentalStateEvaluationId id,
        AppointmentId appointmentId,
        String generalAppearance,
        String behavioralObservation,
        String attitudeTowardExaminer,
        String levelOfConsciousness,
        String orientationStatus,
        String attentionAndConcentration,
        String memoryAssessment,
        String speechCharacteristics,
        String moodDescription,
        String affectDescription,
        String thoughtProcess,
        String thoughtContent,
        String perceptualDisturbances,
        String judgmentAssessment,
        String insightAssessment,
        String psychomotorFindings,
        String examinerRemarks
) {
    public UpdateMentalStateEvaluationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
    }
}
