package com.carelink.domain.supportconversationstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusRegisteredEvent;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusUpdatedEvent;
import org.junit.jupiter.api.Test;

class SupportConversationStatusTest {
    private static SupportConversationStatus sample() {
        return SupportConversationStatus.register(
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SupportConversationStatus aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SupportConversationStatusRegisteredEvent event = assertInstanceOf(SupportConversationStatusRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SupportConversationStatus aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample",
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        SupportConversationStatusUpdatedEvent event = assertInstanceOf(SupportConversationStatusUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SupportConversationStatus original = sample();
        SupportConversationStatus restored = SupportConversationStatus.restore(
                original.id(),
                original.code(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
