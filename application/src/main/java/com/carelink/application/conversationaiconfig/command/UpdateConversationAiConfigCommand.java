package com.carelink.application.conversationaiconfig.command;

import java.util.Objects;

import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record UpdateConversationAiConfigCommand(
        ConversationAiConfigId id,
        SupportConversationId conversationId,
        Boolean aiEnabled,
        LlmModelId preferredModelId
) {
    public UpdateConversationAiConfigCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(aiEnabled, "aiEnabled must not be null");
        Objects.requireNonNull(preferredModelId, "preferredModelId must not be null");
    }
}
