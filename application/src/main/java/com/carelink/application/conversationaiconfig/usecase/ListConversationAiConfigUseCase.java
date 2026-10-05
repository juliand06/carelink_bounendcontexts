package com.carelink.application.conversationaiconfig.usecase;

import java.util.List;

import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;

public class ListConversationAiConfigUseCase {
    private final ConversationAiConfigRepository repository;

    public ListConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        this.repository = repository;
    }

    public List<ConversationAiConfigResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ConversationAiConfigResponse(
                        aggregate.id().value(),
                        aggregate.conversationId().value(),
                        aggregate.aiEnabled(),
                        aggregate.preferredModelId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
