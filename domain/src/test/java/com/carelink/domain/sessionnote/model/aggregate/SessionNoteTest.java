package com.carelink.domain.sessionnote.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.sessionnote.event.SessionNoteRegisteredEvent;
import com.carelink.domain.sessionnote.event.SessionNoteUpdatedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class SessionNoteTest {
    private static SessionNote sample() {
        return SessionNote.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0));
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SessionNote aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SessionNoteRegisteredEvent event = assertInstanceOf(SessionNoteRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SessionNote aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0));
        assertEquals(1, aggregate.domainEvents().size());
        SessionNoteUpdatedEvent event = assertInstanceOf(SessionNoteUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SessionNote original = sample();
        SessionNote restored = SessionNote.restore(
                original.id(),
                original.appointmentId(),
                original.specialistId(),
                original.subjective(),
                original.objective(),
                original.assessment(),
                original.plan(),
                original.supplementaryNotes(),
                original.signedAt(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
