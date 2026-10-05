package com.carelink.domain.country.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.country.event.CountryRegisteredEvent;
import com.carelink.domain.country.event.CountryUpdatedEvent;
import org.junit.jupiter.api.Test;

class CountryTest {
    private static Country sample() {
        return Country.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Country aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        CountryRegisteredEvent event = assertInstanceOf(CountryRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Country aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                "Sample",
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        CountryUpdatedEvent event = assertInstanceOf(CountryUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Country original = sample();
        Country restored = Country.restore(
                original.id(),
                original.name(),
                original.isoCode(),
                original.notes(),
                original.enabled(),
                original.dialPrefix(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
