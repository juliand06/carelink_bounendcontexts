package com.carelink.infrastructure.mentalstateevaluation.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateMentalStateEvaluationRequest(
        @NotNull(message = "appointmentId is required")
        UUID appointmentId,

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

        @NotNull(message = "createdBy is required")
        UUID createdBy
) {
}
