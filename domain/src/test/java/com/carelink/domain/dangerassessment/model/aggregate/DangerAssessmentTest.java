package com.carelink.domain.dangerassessment.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.dangerassessment.event.DangerAssessmentRegisteredEvent;
import com.carelink.domain.dangerassessment.event.DangerAssessmentUpdatedEvent;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DangerAssessmentTest {
    private static DangerAssessment sample() {
        return DangerAssessment.register(
                AppointmentId.generate(),
                DangerLevelId.generate(),
                true,
                true,
                true,
                true,
                true,
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        DangerAssessment aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        DangerAssessmentRegisteredEvent event = assertInstanceOf(DangerAssessmentRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        DangerAssessment aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                AppointmentId.generate(),
                DangerLevelId.generate(),
                true,
                true,
                true,
                true,
                true,
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                SpecialistId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        DangerAssessmentUpdatedEvent event = assertInstanceOf(DangerAssessmentUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        DangerAssessment original = sample();
        DangerAssessment restored = DangerAssessment.restore(
                original.id(),
                original.appointmentId(),
                original.dangerLevelId(),
                original.suicidalIdeation(),
                original.hasSuicidePlan(),
                original.hasSuicideIntent(),
                original.selfHarmBehavior(),
                original.riskToOthers(),
                original.protectiveFactors(),
                original.identifiedRiskFactors(),
                original.clinicalActionsTaken(),
                original.evaluatorObservations(),
                original.evaluatedAt(),
                original.evaluatedBy());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
