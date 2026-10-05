package com.carelink.domain.supportmessage.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.event.SupportMessageRegisteredEvent;
import com.carelink.domain.supportmessage.event.SupportMessageUpdatedEvent;
import org.junit.jupiter.api.Test;

class SupportMessageTest {
    private static SupportMessage sample() {
        return SupportMessage.register(
                SupportConversationId.generate(),
                MessageFormatId.generate(),
                ConversationMemberId.generate(),
                "{\"key\":\"value\"}",
                "{\"key\":\"value\"}");
    }

    @Test
    void shouldRegisterCreatedEvent() {
        SupportMessage aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        SupportMessageRegisteredEvent event = assertInstanceOf(SupportMessageRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        SupportMessage aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationId.generate(),
                MessageFormatId.generate(),
                ConversationMemberId.generate(),
                "{\"key\":\"value\"}",
                "{\"key\":\"value\"}");
        assertEquals(1, aggregate.domainEvents().size());
        SupportMessageUpdatedEvent event = assertInstanceOf(SupportMessageUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        SupportMessage original = sample();
        SupportMessage restored = SupportMessage.restore(
                original.id(),
                original.conversationId(),
                original.messageFormatId(),
                original.memberId(),
                original.payload(),
                original.extraData(),
                original.createdAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
