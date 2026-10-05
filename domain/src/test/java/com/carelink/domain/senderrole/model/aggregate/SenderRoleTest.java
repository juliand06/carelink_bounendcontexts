package com.carelink.domain.senderrole.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.senderrole.event.SenderRoleRegisteredEvent;
import com.carelink.domain.senderrole.event.SenderRoleUpdatedEvent;
import org.junit.jupiter.api.Test;

class SenderRoleTest {
    private static SenderRole sample() {
        return SenderRole.register(
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SenderRole aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SenderRoleRegisteredEvent event = assertInstanceOf(SenderRoleRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SenderRole aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        SenderRoleUpdatedEvent event = assertInstanceOf(SenderRoleUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SenderRole original = sample();
        SenderRole restored = SenderRole.restore(
                original.id(),
                original.label(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
