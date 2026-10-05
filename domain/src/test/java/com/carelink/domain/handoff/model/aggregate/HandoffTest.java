package com.carelink.domain.handoff.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.handoff.event.HandoffRegisteredEvent;
import com.carelink.domain.handoff.event.HandoffUpdatedEvent;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class HandoffTest {
    private static Handoff sample() {
        return Handoff.register(
                SupportConversationId.generate(),
                HandoffStatusId.generate(),
                true,
                "Sample");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        Handoff aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        HandoffRegisteredEvent event = assertInstanceOf(HandoffRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        Handoff aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationId.generate(),
                HandoffStatusId.generate(),
                true,
                "Sample");
        assertEquals(1, aggregate.domainEvents().size());
        HandoffUpdatedEvent event = assertInstanceOf(HandoffUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        Handoff original = sample();
        Handoff restored = Handoff.restore(
                original.id(),
                original.conversationId(),
                original.handoffStatusId(),
                original.originatedFromAi(),
                original.handoffReason(),
                original.createdAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
