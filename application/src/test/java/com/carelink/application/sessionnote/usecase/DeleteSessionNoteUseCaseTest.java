package com.carelink.application.sessionnote.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.sessionnote.exception.SessionNoteNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.sessionnote.event.SessionNoteDeletedEvent;
import com.carelink.domain.sessionnote.model.aggregate.SessionNote;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteSessionNoteUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SessionNote aggregate = SessionNote.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0));
        FakeRepository repository = new FakeRepository(aggregate);
        SessionNoteDeletedEvent event = new DeleteSessionNoteUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SessionNoteNotFoundApplicationException.class,
                () -> new DeleteSessionNoteUseCase(repository).execute(SessionNoteId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SessionNoteRepository {
        private SessionNote aggregate;
        private SessionNote deletedAggregate;

        private FakeRepository(SessionNote aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SessionNote save(SessionNote value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SessionNote> findById(SessionNoteId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SessionNote> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SessionNote value) {
            deletedAggregate = value;
        }

        private SessionNote deletedAggregate() {
            return deletedAggregate;
        }
    }
}
