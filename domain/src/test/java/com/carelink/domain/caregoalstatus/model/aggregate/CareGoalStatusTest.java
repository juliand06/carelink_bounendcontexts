package com.carelink.domain.caregoalstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.caregoalstatus.event.CareGoalStatusRegisteredEvent;
import com.carelink.domain.caregoalstatus.event.CareGoalStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class CareGoalStatusTest {
    private static CareGoalStatus sample() {
        return CareGoalStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        CareGoalStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        CareGoalStatusRegisteredEvent event = assertInstanceOf(CareGoalStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        CareGoalStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        CareGoalStatusUpdatedEvent event = assertInstanceOf(CareGoalStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        CareGoalStatus original = sample();
        CareGoalStatus restored = CareGoalStatus.restore(
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
