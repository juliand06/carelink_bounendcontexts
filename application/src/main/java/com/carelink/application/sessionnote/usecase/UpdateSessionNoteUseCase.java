package com.carelink.application.sessionnote.usecase;

import com.carelink.application.sessionnote.command.UpdateSessionNoteCommand;
import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.application.sessionnote.exception.SessionNoteNotFoundApplicationException;

public class UpdateSessionNoteUseCase {
    private final SessionNoteRepository repository;

    public UpdateSessionNoteUseCase(SessionNoteRepository repository) {
        this.repository = repository;
    }

    public SessionNoteResponse execute(UpdateSessionNoteCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SessionNoteNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.appointmentId(),
                command.specialistId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.supplementaryNotes(),
                command.signedAt());
        var saved = repository.save(aggregate);
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
