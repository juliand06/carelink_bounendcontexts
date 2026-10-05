package com.carelink.domain.supportconversation.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.UUID;

import com.carelink.domain.supportconversation.event.SupportConversationRegisteredEvent;
import com.carelink.domain.supportconversation.event.SupportConversationUpdatedEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import org.junit.jupiter.api.Test;

class SupportConversationTest {
    private static SupportConversation sample() {
        return SupportConversation.register(
                SupportConversationStatusId.generate(),
                UrgencyLevelId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                true,
                LocalDateTime.of(2026, 1, 10, 8, 0),
                UUID.randomUUID());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SupportConversation aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SupportConversationRegisteredEvent event = assertInstanceOf(SupportConversationRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SupportConversation aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationStatusId.generate(),
                UrgencyLevelId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                true,
                LocalDateTime.of(2026, 1, 10, 8, 0),
                UUID.randomUUID());
        assertEquals(1, aggregate.domainEvents().size());
        SupportConversationUpdatedEvent event = assertInstanceOf(SupportConversationUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SupportConversation original = sample();
        SupportConversation restored = SupportConversation.restore(
                original.id(),
                original.conversationStatusId(),
                original.urgencyLevelId(),
                original.lastInteractionAt(),
                original.isClosed(),
                original.closedAt(),
                original.closedBy(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
