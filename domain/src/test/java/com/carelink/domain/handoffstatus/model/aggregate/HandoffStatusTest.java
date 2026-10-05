package com.carelink.domain.handoffstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.handoffstatus.event.HandoffStatusRegisteredEvent;
import com.carelink.domain.handoffstatus.event.HandoffStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class HandoffStatusTest {
    private static HandoffStatus sample() {
        return HandoffStatus.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        HandoffStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HandoffStatusRegisteredEvent event = assertInstanceOf(HandoffStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        HandoffStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        HandoffStatusUpdatedEvent event = assertInstanceOf(HandoffStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        HandoffStatus original = sample();
        HandoffStatus restored = HandoffStatus.restore(
                original.id(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
