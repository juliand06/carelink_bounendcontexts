package com.carelink.domain.mentalstateevaluation.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationRegisteredEvent;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationUpdatedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class MentalStateEvaluationTest {
    private static MentalStateEvaluation sample() {
        return MentalStateEvaluation.register(
                AppointmentId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        MentalStateEvaluation aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        MentalStateEvaluationRegisteredEvent event = assertInstanceOf(MentalStateEvaluationRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        MentalStateEvaluation aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                AppointmentId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        MentalStateEvaluationUpdatedEvent event = assertInstanceOf(MentalStateEvaluationUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        MentalStateEvaluation original = sample();
        MentalStateEvaluation restored = MentalStateEvaluation.restore(
                original.id(),
                original.appointmentId(),
                original.generalAppearance(),
                original.behavioralObservation(),
                original.attitudeTowardExaminer(),
                original.levelOfConsciousness(),
                original.orientationStatus(),
                original.attentionAndConcentration(),
                original.memoryAssessment(),
                original.speechCharacteristics(),
                original.moodDescription(),
                original.affectDescription(),
                original.thoughtProcess(),
                original.thoughtContent(),
                original.perceptualDisturbances(),
                original.judgmentAssessment(),
                original.insightAssessment(),
                original.psychomotorFindings(),
                original.examinerRemarks(),
                original.createdAt(),
                original.createdBy());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
