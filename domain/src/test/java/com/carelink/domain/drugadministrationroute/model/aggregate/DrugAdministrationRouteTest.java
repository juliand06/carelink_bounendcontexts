package com.carelink.domain.drugadministrationroute.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteRegisteredEvent;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteUpdatedEvent;
import org.junit.jupiter.api.Test;

class DrugAdministrationRouteTest {
    private static DrugAdministrationRoute sample() {
        return DrugAdministrationRoute.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        DrugAdministrationRoute aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        DrugAdministrationRouteRegisteredEvent event = assertInstanceOf(DrugAdministrationRouteRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        DrugAdministrationRoute aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        DrugAdministrationRouteUpdatedEvent event = assertInstanceOf(DrugAdministrationRouteUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        DrugAdministrationRoute original = sample();
        DrugAdministrationRoute restored = DrugAdministrationRoute.restore(
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
