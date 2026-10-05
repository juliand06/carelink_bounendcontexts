package com.carelink.domain.classificationsystem.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.classificationsystem.event.ClassificationSystemRegisteredEvent;
import com.carelink.domain.classificationsystem.event.ClassificationSystemUpdatedEvent;
import org.junit.jupiter.api.Test;

class ClassificationSystemTest {
    private static ClassificationSystem sample() {
        return ClassificationSystem.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ClassificationSystem aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ClassificationSystemRegisteredEvent event = assertInstanceOf(ClassificationSystemRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ClassificationSystem aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample",
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        ClassificationSystemUpdatedEvent event = assertInstanceOf(ClassificationSystemUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ClassificationSystem original = sample();
        ClassificationSystem restored = ClassificationSystem.restore(
                original.id(),
                original.code(),
                original.label(),
                original.active(),
                original.edition(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
