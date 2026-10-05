package com.carelink.application.supportmessage.usecase;

import java.time.LocalDateTime;

import com.carelink.application.supportmessage.exception.SupportMessageNotFoundApplicationException;
import com.carelink.domain.supportmessage.event.SupportMessageDeletedEvent;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;

public class DeleteSupportMessageUseCase {
    private final SupportMessageRepository repository;

    public DeleteSupportMessageUseCase(SupportMessageRepository repository) {
        this.repository = repository;
    }

    public SupportMessageDeletedEvent execute(SupportMessageId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SupportMessageNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SupportMessageDeletedEvent(id, LocalDateTime.now());
    }
}
