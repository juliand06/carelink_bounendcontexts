package com.carelink.domain.municipality.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.event.MunicipalityRegisteredEvent;
import com.carelink.domain.municipality.event.MunicipalityUpdatedEvent;
import org.junit.jupiter.api.Test;

class MunicipalityTest {
    private static Municipality sample() {
        return Municipality.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                DepartmentId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Municipality aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        MunicipalityRegisteredEvent event = assertInstanceOf(MunicipalityRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Municipality aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                "Sample",
                true,
                DepartmentId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        MunicipalityUpdatedEvent event = assertInstanceOf(MunicipalityUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Municipality original = sample();
        Municipality restored = Municipality.restore(
                original.id(),
                original.name(),
                original.isoCode(),
                original.notes(),
                original.enabled(),
                original.departmentId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
