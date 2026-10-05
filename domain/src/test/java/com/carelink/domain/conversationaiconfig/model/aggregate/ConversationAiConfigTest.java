package com.carelink.domain.conversationaiconfig.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigRegisteredEvent;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigUpdatedEvent;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class ConversationAiConfigTest {
    private static ConversationAiConfig sample() {
        return ConversationAiConfig.register(
                SupportConversationId.generate(),
                true,
                LlmModelId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        ConversationAiConfig aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        ConversationAiConfigRegisteredEvent event = assertInstanceOf(ConversationAiConfigRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        ConversationAiConfig aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationId.generate(),
                true,
                LlmModelId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ConversationAiConfigUpdatedEvent event = assertInstanceOf(ConversationAiConfigUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        ConversationAiConfig original = sample();
        ConversationAiConfig restored = ConversationAiConfig.restore(
                original.id(),
                original.conversationId(),
                original.aiEnabled(),
                original.preferredModelId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
