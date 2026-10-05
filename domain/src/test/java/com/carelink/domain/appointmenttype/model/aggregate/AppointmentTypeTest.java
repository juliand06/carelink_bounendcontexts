package com.carelink.domain.appointmenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointmenttype.event.AppointmentTypeRegisteredEvent;
import com.carelink.domain.appointmenttype.event.AppointmentTypeUpdatedEvent;
import org.junit.jupiter.api.Test;

class AppointmentTypeTest {
    private static AppointmentType sample() {
        return AppointmentType.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        AppointmentType aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentTypeRegisteredEvent event = assertInstanceOf(AppointmentTypeRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        AppointmentType aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentTypeUpdatedEvent event = assertInstanceOf(AppointmentTypeUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        AppointmentType original = sample();
        AppointmentType restored = AppointmentType.restore(
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
