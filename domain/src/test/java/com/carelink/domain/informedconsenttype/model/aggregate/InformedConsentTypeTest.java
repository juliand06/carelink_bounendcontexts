package com.carelink.domain.informedconsenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.informedconsenttype.event.InformedConsentTypeRegisteredEvent;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeUpdatedEvent;
import org.junit.jupiter.api.Test;

class InformedConsentTypeTest {
    private static InformedConsentType sample() {
        return InformedConsentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        InformedConsentType aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        InformedConsentTypeRegisteredEvent event = assertInstanceOf(InformedConsentTypeRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        InformedConsentType aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        InformedConsentTypeUpdatedEvent event = assertInstanceOf(InformedConsentTypeUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        InformedConsentType original = sample();
        InformedConsentType restored = InformedConsentType.restore(
                original.id(),
                original.code(),
                original.label(),
                original.active(),
                original.description(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
