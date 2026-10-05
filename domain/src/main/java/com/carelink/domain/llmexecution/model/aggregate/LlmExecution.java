package com.carelink.domain.llmexecution.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.llmexecution.event.LlmExecutionRegisteredEvent;
import com.carelink.domain.llmexecution.event.LlmExecutionUpdatedEvent;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public class LlmExecution extends AggregateRoot {
    private final LlmExecutionId id;
    private SupportConversationId conversationId;
    private SupportMessageId messageId;
    private LlmModelId modelId;
    private LlmExecutionStatusId executionStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private LlmExecution(
            LlmExecutionId id,
            SupportConversationId conversationId,
            SupportMessageId messageId,
            LlmModelId modelId,
            LlmExecutionStatusId executionStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageId = Objects.requireNonNull(messageId, "messageId must not be null");
        this.modelId = Objects.requireNonNull(modelId, "modelId must not be null");
        this.executionStatusId = Objects.requireNonNull(executionStatusId, "executionStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static LlmExecution register(
            SupportConversationId conversationId,
            SupportMessageId messageId,
            LlmModelId modelId,
            LlmExecutionStatusId executionStatusId) {
        LlmExecutionId id = LlmExecutionId.generate();
        LocalDateTime now = LocalDateTime.now();
        LlmExecution aggregate = new LlmExecution(
                id,
                conversationId,
                messageId,
                modelId,
                executionStatusId,
                now,
                now);
        aggregate.recordEvent(new LlmExecutionRegisteredEvent(id, now));
        return aggregate;
    }

    public static LlmExecution restore(
            LlmExecutionId id,
            SupportConversationId conversationId,
            SupportMessageId messageId,
            LlmModelId modelId,
            LlmExecutionStatusId executionStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new LlmExecution(
                id,
                conversationId,
                messageId,
                modelId,
                executionStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            SupportConversationId conversationId,
            SupportMessageId messageId,
            LlmModelId modelId,
            LlmExecutionStatusId executionStatusId) {
        this.conversationId = Objects.requireNonNull(conversationId, "conversationId must not be null");
        this.messageId = Objects.requireNonNull(messageId, "messageId must not be null");
        this.modelId = Objects.requireNonNull(modelId, "modelId must not be null");
        this.executionStatusId = Objects.requireNonNull(executionStatusId, "executionStatusId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new LlmExecutionUpdatedEvent(
                this.id,
                this.conversationId,
                this.messageId,
                this.modelId,
                this.executionStatusId,
                now));
    }

    public LlmExecutionId id() {
        return id;
    }

    public SupportConversationId conversationId() {
        return conversationId;
    }

    public SupportMessageId messageId() {
        return messageId;
    }

    public LlmModelId modelId() {
        return modelId;
    }

    public LlmExecutionStatusId executionStatusId() {
        return executionStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
