package com.carelink.domain.iddocumenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.iddocumenttype.event.IdDocumentTypeRegisteredEvent;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeUpdatedEvent;
import org.junit.jupiter.api.Test;

class IdDocumentTypeTest {
    private static IdDocumentType sample() {
        return IdDocumentType.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterCreatedEvent() {
        IdDocumentType aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        IdDocumentTypeRegisteredEvent event = assertInstanceOf(IdDocumentTypeRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        IdDocumentType aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        IdDocumentTypeUpdatedEvent event = assertInstanceOf(IdDocumentTypeUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        IdDocumentType original = sample();
        IdDocumentType restored = IdDocumentType.restore(
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
