package com.carelink.domain.biologicalsex.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.biologicalsex.event.BiologicalSexRegisteredEvent;
import com.carelink.domain.biologicalsex.event.BiologicalSexUpdatedEvent;
import org.junit.jupiter.api.Test;

class BiologicalSexTest {
    private static BiologicalSex sample() {
        return BiologicalSex.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        BiologicalSex aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        BiologicalSexRegisteredEvent event = assertInstanceOf(BiologicalSexRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        BiologicalSex aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        BiologicalSexUpdatedEvent event = assertInstanceOf(BiologicalSexUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        BiologicalSex original = sample();
        BiologicalSex restored = BiologicalSex.restore(
                original.id(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
