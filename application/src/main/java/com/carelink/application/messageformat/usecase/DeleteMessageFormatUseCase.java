package com.carelink.application.messageformat.usecase;

import java.time.LocalDateTime;

import com.carelink.application.messageformat.exception.MessageFormatNotFoundApplicationException;
import com.carelink.domain.messageformat.event.MessageFormatDeletedEvent;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;

public class DeleteMessageFormatUseCase {
    private final MessageFormatRepository repository;

    public DeleteMessageFormatUseCase(MessageFormatRepository repository) {
        this.repository = repository;
    }

    public MessageFormatDeletedEvent execute(MessageFormatId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MessageFormatNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MessageFormatDeletedEvent(id, LocalDateTime.now());
    }
}
