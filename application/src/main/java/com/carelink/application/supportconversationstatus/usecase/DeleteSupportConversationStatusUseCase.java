package com.carelink.application.supportconversationstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.supportconversationstatus.exception.SupportConversationStatusNotFoundApplicationException;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusDeletedEvent;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;

public class DeleteSupportConversationStatusUseCase {
    private final SupportConversationStatusRepository repository;

    public DeleteSupportConversationStatusUseCase(SupportConversationStatusRepository repository) {
        this.repository = repository;
    }

    public SupportConversationStatusDeletedEvent execute(SupportConversationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportConversationStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SupportConversationStatusDeletedEvent(id, LocalDateTime.now());
    }
}
