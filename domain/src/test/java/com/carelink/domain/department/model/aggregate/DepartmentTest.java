package com.carelink.domain.department.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.event.DepartmentRegisteredEvent;
import com.carelink.domain.department.event.DepartmentUpdatedEvent;
import org.junit.jupiter.api.Test;

class DepartmentTest {
    private static Department sample() {
        return Department.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                CountryId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Department aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        DepartmentRegisteredEvent event = assertInstanceOf(DepartmentRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Department aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                "Sample",
                true,
                CountryId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        DepartmentUpdatedEvent event = assertInstanceOf(DepartmentUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Department original = sample();
        Department restored = Department.restore(
                original.id(),
                original.name(),
                original.isoCode(),
                original.notes(),
                original.enabled(),
                original.countryId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
