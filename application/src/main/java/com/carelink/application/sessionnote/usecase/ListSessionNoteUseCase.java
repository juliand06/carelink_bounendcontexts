package com.carelink.application.sessionnote.usecase;

import java.util.List;

import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;

public class ListSessionNoteUseCase {
    private final SessionNoteRepository repository;

    public ListSessionNoteUseCase(SessionNoteRepository repository) {
        this.repository = repository;
    }

    public List<SessionNoteResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SessionNoteResponse(
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
                        aggregate.updatedAt()))
                .toList();
    }
}
