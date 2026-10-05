package com.carelink.domain.mentalstateevaluation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;

public record MentalStateEvaluationUpdatedEvent(
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
        String examinerRemarks,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MentalStateEvaluationUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(appointmentId, "appointmentId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
