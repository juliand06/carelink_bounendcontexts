package com.carelink.application.conversationaiconfig.usecase;

import com.carelink.application.conversationaiconfig.command.UpdateConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.application.conversationaiconfig.exception.ConversationAiConfigNotFoundApplicationException;

public class UpdateConversationAiConfigUseCase {
    private final ConversationAiConfigRepository repository;

    public UpdateConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        this.repository = repository;
    }

    public ConversationAiConfigResponse execute(UpdateConversationAiConfigCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConversationAiConfigNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.aiEnabled(),
                command.preferredModelId());
        var saved = repository.save(aggregate);
        return new ConversationAiConfigResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.aiEnabled(),
                saved.preferredModelId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
