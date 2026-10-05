package com.carelink.domain.healthrecordstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusRegisteredEvent;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class HealthRecordStatusTest {
    private static HealthRecordStatus sample() {
        return HealthRecordStatus.register(
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        HealthRecordStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HealthRecordStatusRegisteredEvent event = assertInstanceOf(HealthRecordStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        HealthRecordStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        HealthRecordStatusUpdatedEvent event = assertInstanceOf(HealthRecordStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        HealthRecordStatus original = sample();
        HealthRecordStatus restored = HealthRecordStatus.restore(
                original.id(),
                original.code(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
