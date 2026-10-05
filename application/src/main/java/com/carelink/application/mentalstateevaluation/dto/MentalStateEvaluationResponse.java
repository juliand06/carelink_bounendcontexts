package com.carelink.application.mentalstateevaluation.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MentalStateEvaluationResponse(
        UUID id,
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
        LocalDateTime createdAt,
        UUID createdBy
) {
}
