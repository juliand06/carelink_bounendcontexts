package com.carelink.domain.appointment.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.appointment.event.AppointmentRegisteredEvent;
import com.carelink.domain.appointment.event.AppointmentUpdatedEvent;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class AppointmentTest {
    private static Appointment sample() {
        return Appointment.register(
                HealthRecordId.generate(),
                SpecialistId.generate(),
                AppointmentTypeId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                "Sample",
                AppointmentModalityId.generate(),
                AppointmentStatusId.generate(),
                SpecialistId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Appointment aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentRegisteredEvent event = assertInstanceOf(AppointmentRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Appointment aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                HealthRecordId.generate(),
                SpecialistId.generate(),
                AppointmentTypeId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                "Sample",
                AppointmentModalityId.generate(),
                AppointmentStatusId.generate(),
                SpecialistId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        AppointmentUpdatedEvent event = assertInstanceOf(AppointmentUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Appointment original = sample();
        Appointment restored = Appointment.restore(
                original.id(),
                original.healthRecordId(),
                original.specialistId(),
                original.appointmentTypeId(),
                original.startedAt(),
                original.endedAt(),
                original.chiefComplaint(),
                original.currentCondition(),
                original.modalityId(),
                original.statusId(),
                original.createdAt(),
                original.createdBy(),
                original.updatedAt(),
                original.updatedBy());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
