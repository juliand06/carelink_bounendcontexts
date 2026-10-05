package com.carelink.application.conversationaiconfig.usecase;

import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.application.conversationaiconfig.exception.ConversationAiConfigNotFoundApplicationException;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;

public class GetConversationAiConfigByIdUseCase {
    private final ConversationAiConfigRepository repository;

    public GetConversationAiConfigByIdUseCase(ConversationAiConfigRepository repository) {
        this.repository = repository;
    }

    public ConversationAiConfigResponse execute(ConversationAiConfigId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationAiConfigNotFoundApplicationException(id.value().toString()));
        return new ConversationAiConfigResponse(
                aggregate.id().value(),
                aggregate.conversationId().value(),
                aggregate.aiEnabled(),
                aggregate.preferredModelId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
