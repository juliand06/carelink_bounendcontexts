package com.carelink.domain.dangerassessment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public record DangerAssessmentUpdatedEvent(
        DangerAssessmentId id,
        AppointmentId appointmentId,
        DangerLevelId dangerLevelId,
        Boolean suicidalIdeation,
        Boolean hasSuicidePlan,
        Boolean hasSuicideIntent,
        Boolean selfHarmBehavior,
        Boolean riskToOthers,
        String protectiveFactors,
        String identifiedRiskFactors,
        String clinicalActionsTaken,
        String evaluatorObservations,
        SpecialistId evaluatedBy,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DangerAssessmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(dangerLevelId, "dangerLevelId must not be null");
        Objects.requireNonNull(suicidalIdeation, "suicidalIdeation must not be null");
        Objects.requireNonNull(hasSuicidePlan, "hasSuicidePlan must not be null");
        Objects.requireNonNull(hasSuicideIntent, "hasSuicideIntent must not be null");
        Objects.requireNonNull(selfHarmBehavior, "selfHarmBehavior must not be null");
        Objects.requireNonNull(riskToOthers, "riskToOthers must not be null");
        Objects.requireNonNull(evaluatedBy, "evaluatedBy must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
