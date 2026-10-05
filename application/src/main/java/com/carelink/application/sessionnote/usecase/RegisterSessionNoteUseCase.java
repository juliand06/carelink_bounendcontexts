package com.carelink.application.sessionnote.usecase;

import com.carelink.application.sessionnote.command.RegisterSessionNoteCommand;
import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.domain.sessionnote.model.aggregate.SessionNote;

public class RegisterSessionNoteUseCase {
    private final SessionNoteRepository repository;

    public RegisterSessionNoteUseCase(SessionNoteRepository repository) {
        this.repository = repository;
    }

    public SessionNoteResponse execute(RegisterSessionNoteCommand command) {
        SessionNote aggregate = SessionNote.register(
                command.appointmentId(),
                command.specialistId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.supplementaryNotes(),
                command.signedAt());
        SessionNote saved = repository.save(aggregate);
        return new SessionNoteResponse(
                saved.id().value(),
                saved.appointmentId().value(),
                saved.specialistId().value(),
                saved.subjective(),
                saved.objective(),
                saved.assessment(),
                saved.plan(),
                saved.supplementaryNotes(),
                saved.signedAt(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
