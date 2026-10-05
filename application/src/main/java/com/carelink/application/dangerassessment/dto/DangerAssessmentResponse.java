package com.carelink.application.dangerassessment.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record DangerAssessmentResponse(
        UUID id,
        UUID appointmentId,
        UUID dangerLevelId,
        Boolean suicidalIdeation,
        Boolean hasSuicidePlan,
        Boolean hasSuicideIntent,
        Boolean selfHarmBehavior,
        Boolean riskToOthers,
        String protectiveFactors,
        String identifiedRiskFactors,
        String clinicalActionsTaken,
        String evaluatorObservations,
        LocalDateTime evaluatedAt,
        UUID evaluatedBy
) {
}
