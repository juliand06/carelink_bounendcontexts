package com.carelink.application.sessionnote.usecase;

import java.time.LocalDateTime;

import com.carelink.application.sessionnote.exception.SessionNoteNotFoundApplicationException;
import com.carelink.domain.sessionnote.event.SessionNoteDeletedEvent;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;

public class DeleteSessionNoteUseCase {
    private final SessionNoteRepository repository;

    public DeleteSessionNoteUseCase(SessionNoteRepository repository) {
        this.repository = repository;
    }

    public SessionNoteDeletedEvent execute(SessionNoteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SessionNoteNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SessionNoteDeletedEvent(id, LocalDateTime.now());
    }
}
