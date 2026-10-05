package com.carelink.domain.urgencylevel.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.urgencylevel.event.UrgencyLevelRegisteredEvent;
import com.carelink.domain.urgencylevel.event.UrgencyLevelUpdatedEvent;
import org.junit.jupiter.api.Test;

class UrgencyLevelTest {
    private static UrgencyLevel sample() {
        return UrgencyLevel.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        UrgencyLevel aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        UrgencyLevelRegisteredEvent event = assertInstanceOf(UrgencyLevelRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        UrgencyLevel aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        UrgencyLevelUpdatedEvent event = assertInstanceOf(UrgencyLevelUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        UrgencyLevel original = sample();
        UrgencyLevel restored = UrgencyLevel.restore(
                original.id(),
                original.code(),
                original.label(),
                original.active(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
