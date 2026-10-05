package com.carelink.application.sessionnote.usecase;

import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.application.sessionnote.exception.SessionNoteNotFoundApplicationException;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;

public class GetSessionNoteByIdUseCase {
    private final SessionNoteRepository repository;

    public GetSessionNoteByIdUseCase(SessionNoteRepository repository) {
        this.repository = repository;
    }

    public SessionNoteResponse execute(SessionNoteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SessionNoteNotFoundApplicationException(id.value().toString()));
        return new SessionNoteResponse(
                aggregate.id().value(),
                aggregate.appointmentId().value(),
                aggregate.specialistId().value(),
                aggregate.subjective(),
                aggregate.objective(),
                aggregate.assessment(),
                aggregate.plan(),
                aggregate.supplementaryNotes(),
                aggregate.signedAt(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
