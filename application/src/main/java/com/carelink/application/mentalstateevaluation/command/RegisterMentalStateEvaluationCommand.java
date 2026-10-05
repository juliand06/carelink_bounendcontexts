package com.carelink.application.mentalstateevaluation.command;

import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record RegisterMentalStateEvaluationCommand(
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
        String examinerRemarks,
        SpecialistId createdBy
) {
    public RegisterMentalStateEvaluationCommand {
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
    }
}
