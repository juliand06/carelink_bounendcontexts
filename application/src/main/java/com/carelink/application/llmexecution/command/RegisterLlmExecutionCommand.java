package com.carelink.application.llmexecution.command;

import java.util.Objects;

import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public record RegisterLlmExecutionCommand(
        SupportConversationId conversationId,
        SupportMessageId messageId,
        LlmModelId modelId,
        LlmExecutionStatusId executionStatusId
) {
    public RegisterLlmExecutionCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageId, "messageId must not be null");
        Objects.requireNonNull(modelId, "modelId must not be null");
        Objects.requireNonNull(executionStatusId, "executionStatusId must not be null");
    }
}
