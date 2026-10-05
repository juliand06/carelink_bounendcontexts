package com.carelink.domain.llmexecution.model.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import com.carelink.domain.llmexecution.event.LlmExecutionRegisteredEvent;
import com.carelink.domain.llmexecution.event.LlmExecutionUpdatedEvent;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import org.junit.jupiter.api.Test;

class LlmExecutionTest {
    private static LlmExecution sample() {
        return LlmExecution.register(
                SupportConversationId.generate(),
                SupportMessageId.generate(),
                LlmModelId.generate(),
                LlmExecutionStatusId.generate());
    }

    @Test
    void shouldRegisterCreatedEvent() {
        LlmExecution aggregate = sample();
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionRegisteredEvent event = assertInstanceOf(LlmExecutionRegisteredEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRecordUpdatedEventOnUpdate() {
        LlmExecution aggregate = sample();
        aggregate.clearDomainEvents();
        aggregate.update(
                SupportConversationId.generate(),
                SupportMessageId.generate(),
                LlmModelId.generate(),
                LlmExecutionStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        LlmExecutionUpdatedEvent event = assertInstanceOf(LlmExecutionUpdatedEvent.class, aggregate.domainEvents().get(0));
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRestoreWithoutRecordingEvents() {
        LlmExecution original = sample();
        LlmExecution restored = LlmExecution.restore(
                original.id(),
                original.conversationId(),
                original.messageId(),
                original.modelId(),
                original.executionStatusId(),
                original.createdAt(),
                original.updatedAt());
        assertEquals(original.id(), restored.id());
        assertTrue(restored.domainEvents().isEmpty());
    }
}
