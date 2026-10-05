package com.carelink.application.conversationaiconfig.usecase;

import com.carelink.application.conversationaiconfig.command.RegisterConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;

public class RegisterConversationAiConfigUseCase {
    private final ConversationAiConfigRepository repository;

    public RegisterConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        this.repository = repository;
    }

    public ConversationAiConfigResponse execute(RegisterConversationAiConfigCommand command) {
        ConversationAiConfig aggregate = ConversationAiConfig.register(
                command.conversationId(),
                command.aiEnabled(),
                command.preferredModelId());
        ConversationAiConfig saved = repository.save(aggregate);
        return new ConversationAiConfigResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.aiEnabled(),
                saved.preferredModelId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
