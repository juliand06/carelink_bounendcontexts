package com.carelink.application.conversationaiconfig.usecase;

import java.time.LocalDateTime;

import com.carelink.application.conversationaiconfig.exception.ConversationAiConfigNotFoundApplicationException;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigDeletedEvent;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;

public class DeleteConversationAiConfigUseCase {
    private final ConversationAiConfigRepository repository;

    public DeleteConversationAiConfigUseCase(ConversationAiConfigRepository repository) {
        this.repository = repository;
    }

    public ConversationAiConfigDeletedEvent execute(ConversationAiConfigId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationAiConfigNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ConversationAiConfigDeletedEvent(id, LocalDateTime.now());
    }
}
