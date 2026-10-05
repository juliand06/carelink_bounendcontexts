package com.carelink.domain.handoffassignment.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentRegisteredEvent;
import com.carelink.domain.handoffassignment.event.HandoffAssignmentUpdatedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class HandoffAssignmentTest {
    private static HandoffAssignment sample() {
        return HandoffAssignment.register(
                HandoffId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        HandoffAssignment aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HandoffAssignmentRegisteredEvent event = assertInstanceOf(HandoffAssignmentRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        HandoffAssignment aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                HandoffId.generate(),
                SpecialistId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        HandoffAssignmentUpdatedEvent event = assertInstanceOf(HandoffAssignmentUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        HandoffAssignment original = sample();
        HandoffAssignment restored = HandoffAssignment.restore(
                original.id(),
                original.handoffId(),
                original.specialistId(),
                original.assignedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
