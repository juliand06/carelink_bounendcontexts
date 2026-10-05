package com.carelink.domain.careplanstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.careplanstatus.event.CarePlanStatusRegisteredEvent;
import com.carelink.domain.careplanstatus.event.CarePlanStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class CarePlanStatusTest {
    private static CarePlanStatus sample() {
        return CarePlanStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        CarePlanStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        CarePlanStatusRegisteredEvent event = assertInstanceOf(CarePlanStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        CarePlanStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        CarePlanStatusUpdatedEvent event = assertInstanceOf(CarePlanStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        CarePlanStatus original = sample();
        CarePlanStatus restored = CarePlanStatus.restore(
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
