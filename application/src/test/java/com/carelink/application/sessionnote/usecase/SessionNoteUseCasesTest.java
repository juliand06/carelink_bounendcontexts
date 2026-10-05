package com.carelink.application.sessionnote.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.sessionnote.command.RegisterSessionNoteCommand;
import com.carelink.application.sessionnote.command.UpdateSessionNoteCommand;
import com.carelink.application.sessionnote.dto.SessionNoteResponse;
import com.carelink.application.sessionnote.exception.SessionNoteNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.sessionnote.event.SessionNoteDeletedEvent;
import com.carelink.domain.sessionnote.model.aggregate.SessionNote;
import com.carelink.domain.sessionnote.model.valueobject.SessionNoteId;
import com.carelink.domain.sessionnote.port.repository.SessionNoteRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class SessionNoteUseCasesTest {
    private static SessionNote sample() {
        return SessionNote.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0));
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SessionNoteResponse response = new RegisterSessionNoteUseCase(repository).execute(
                new RegisterSessionNoteCommand(
                AppointmentId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    LocalDateTime.of(2026, 1, 10, 8, 0)));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SessionNote aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SessionNoteResponse response = new GetSessionNoteByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SessionNoteNotFoundApplicationException.class,
                () -> new GetSessionNoteByIdUseCase(repository).execute(SessionNoteId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSessionNoteUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SessionNote aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SessionNoteResponse response = new UpdateSessionNoteUseCase(repository).execute(
                new UpdateSessionNoteCommand(
                aggregate.id(),
                    AppointmentId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    LocalDateTime.of(2026, 1, 10, 8, 0)));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SessionNote other = sample();
        assertThrows(SessionNoteNotFoundApplicationException.class,
                () -> new UpdateSessionNoteUseCase(repository).execute(
                        new UpdateSessionNoteCommand(
                        other.id(),
                        AppointmentId.generate(),
                        SpecialistId.generate(),
                        "Sample",
                        "Sample",
                        "Sample",
                        "Sample",
                        "Sample",
                        LocalDateTime.of(2026, 1, 10, 8, 0))));
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
