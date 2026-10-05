package com.carelink.domain.handoffstatusaudit.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditRegisteredEvent;
import com.carelink.domain.handoffstatusaudit.event.HandoffStatusAuditUpdatedEvent;
import org.junit.jupiter.api.Test;

class HandoffStatusAuditTest {
    private static HandoffStatusAudit sample() {
        return HandoffStatusAudit.register(
                HandoffId.generate(),
                HandoffStatusId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        HandoffStatusAudit aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HandoffStatusAuditRegisteredEvent event = assertInstanceOf(HandoffStatusAuditRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        HandoffStatusAudit aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                HandoffId.generate(),
                HandoffStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        HandoffStatusAuditUpdatedEvent event = assertInstanceOf(HandoffStatusAuditUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        HandoffStatusAudit original = sample();
        HandoffStatusAudit restored = HandoffStatusAudit.restore(
                original.id(),
                original.handoffId(),
                original.handoffStatusId(),
                original.recordedAt(),
                original.transitionedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
