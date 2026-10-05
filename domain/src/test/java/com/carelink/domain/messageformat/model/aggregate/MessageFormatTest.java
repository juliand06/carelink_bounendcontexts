package com.carelink.domain.messageformat.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.messageformat.event.MessageFormatRegisteredEvent;
import com.carelink.domain.messageformat.event.MessageFormatUpdatedEvent;
import org.junit.jupiter.api.Test;

class MessageFormatTest {
    private static MessageFormat sample() {
        return MessageFormat.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        MessageFormat aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        MessageFormatRegisteredEvent event = assertInstanceOf(MessageFormatRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        MessageFormat aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        MessageFormatUpdatedEvent event = assertInstanceOf(MessageFormatUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        MessageFormat original = sample();
        MessageFormat restored = MessageFormat.restore(
                original.id(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
