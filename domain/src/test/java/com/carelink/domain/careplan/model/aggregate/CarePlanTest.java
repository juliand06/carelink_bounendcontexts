package com.carelink.domain.careplan.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.event.CarePlanRegisteredEvent;
import com.carelink.domain.careplan.event.CarePlanUpdatedEvent;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class CarePlanTest {
    private static CarePlan sample() {
        return CarePlan.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 1, 1),
                CarePlanStatusId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        CarePlan aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        CarePlanRegisteredEvent event = assertInstanceOf(CarePlanRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        CarePlan aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 1, 1),
                CarePlanStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        CarePlanUpdatedEvent event = assertInstanceOf(CarePlanUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        CarePlan original = sample();
        CarePlan restored = CarePlan.restore(
                original.id(),
                original.appointmentId(),
                original.specialistId(),
                original.planTitle(),
                original.planDescription(),
                original.startDate(),
                original.projectedEndDate(),
                original.carePlanStatusId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
