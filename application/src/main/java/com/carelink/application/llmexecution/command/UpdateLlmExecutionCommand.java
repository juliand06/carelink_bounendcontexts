package com.carelink.application.llmexecution.command;

import java.util.Objects;

import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public record UpdateLlmExecutionCommand(
        LlmExecutionId id,
        SupportConversationId conversationId,
        SupportMessageId messageId,
        LlmModelId modelId,
        LlmExecutionStatusId executionStatusId
) {
    public UpdateLlmExecutionCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(executionStatusId, "executionStatusId must not be null");
    }
}
