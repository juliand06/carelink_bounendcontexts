package com.carelink.domain.dangerlevel.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.dangerlevel.event.DangerLevelRegisteredEvent;
import com.carelink.domain.dangerlevel.event.DangerLevelUpdatedEvent;
import org.junit.jupiter.api.Test;

class DangerLevelTest {
    private static DangerLevel sample() {
        return DangerLevel.register(
                "Sample",
                "Sample",
                true,
                1);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        DangerLevel aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        DangerLevelRegisteredEvent event = assertInstanceOf(DangerLevelRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        DangerLevel aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true,
                1);
        assertEquals(1, aggregate.domainEvents().size());
        DangerLevelUpdatedEvent event = assertInstanceOf(DangerLevelUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        DangerLevel original = sample();
        DangerLevel restored = DangerLevel.restore(
                original.id(),
                original.code(),
                original.label(),
                original.active(),
                original.severityRank(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
