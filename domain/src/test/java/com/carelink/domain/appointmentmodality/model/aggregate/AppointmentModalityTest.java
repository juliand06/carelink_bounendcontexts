package com.carelink.domain.appointmentmodality.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointmentmodality.event.AppointmentModalityRegisteredEvent;
import com.carelink.domain.appointmentmodality.event.AppointmentModalityUpdatedEvent;
import org.junit.jupiter.api.Test;

class AppointmentModalityTest {
    private static AppointmentModality sample() {
        return AppointmentModality.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        AppointmentModality aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentModalityRegisteredEvent event = assertInstanceOf(AppointmentModalityRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        AppointmentModality aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentModalityUpdatedEvent event = assertInstanceOf(AppointmentModalityUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        AppointmentModality original = sample();
        AppointmentModality restored = AppointmentModality.restore(
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
