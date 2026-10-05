package com.carelink.domain.appointmentstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointmentstatus.event.AppointmentStatusRegisteredEvent;
import com.carelink.domain.appointmentstatus.event.AppointmentStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class AppointmentStatusTest {
    private static AppointmentStatus sample() {
        return AppointmentStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        AppointmentStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentStatusRegisteredEvent event = assertInstanceOf(AppointmentStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        AppointmentStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentStatusUpdatedEvent event = assertInstanceOf(AppointmentStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        AppointmentStatus original = sample();
        AppointmentStatus restored = AppointmentStatus.restore(
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
