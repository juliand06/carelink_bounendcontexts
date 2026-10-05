package com.carelink.application.supportconversation.usecase;

import java.time.LocalDateTime;

import com.carelink.application.supportconversation.exception.SupportConversationNotFoundApplicationException;
import com.carelink.domain.supportconversation.event.SupportConversationDeletedEvent;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;

public class DeleteSupportConversationUseCase {
    private final SupportConversationRepository repository;

    public DeleteSupportConversationUseCase(SupportConversationRepository repository) {
        this.repository = repository;
    }

    public SupportConversationDeletedEvent execute(SupportConversationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportConversationNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SupportConversationDeletedEvent(id, LocalDateTime.now());
    }
}
