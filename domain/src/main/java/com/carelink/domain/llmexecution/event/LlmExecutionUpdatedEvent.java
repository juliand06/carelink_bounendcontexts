package com.carelink.domain.llmexecution.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.event.DomainEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public record LlmExecutionUpdatedEvent(
        LlmExecutionId id,
        SupportConversationId conversationId,
        SupportMessageId messageId,
        LlmModelId modelId,
        LlmExecutionStatusId executionStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public LlmExecutionUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(executionStatusId, "executionStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
