package com.carelink.infrastructure.dangerassessment.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record CreateDangerAssessmentRequest(
        @NotNull(message = "appointmentId is required")
        UUID appointmentId,

        @NotNull(message = "dangerLevelId is required")
        UUID dangerLevelId,

        @NotNull(message = "suicidalIdeation is required")
        Boolean suicidalIdeation,

        @NotNull(message = "hasSuicidePlan is required")
        Boolean hasSuicidePlan,

        @NotNull(message = "hasSuicideIntent is required")
        Boolean hasSuicideIntent,

        @NotNull(message = "selfHarmBehavior is required")
        Boolean selfHarmBehavior,

        @NotNull(message = "riskToOthers is required")
        Boolean riskToOthers,

        String protectiveFactors,

        String identifiedRiskFactors,

        String clinicalActionsTaken,

        String evaluatorObservations,

        @NotNull(message = "evaluatedBy is required")
        UUID evaluatedBy
) {
}
