package com.carelink.application.urgencylevel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.urgencylevel.command.RegisterUrgencyLevelCommand;
import com.carelink.application.urgencylevel.command.UpdateUrgencyLevelCommand;
import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.application.urgencylevel.exception.UrgencyLevelNotFoundApplicationException;
import com.carelink.domain.urgencylevel.event.UrgencyLevelDeletedEvent;
import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import org.junit.jupiter.api.Test;

class UrgencyLevelUseCasesTest {
    private static UrgencyLevel sample() {
        return UrgencyLevel.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        UrgencyLevelResponse response = new RegisterUrgencyLevelUseCase(repository).execute(
                new RegisterUrgencyLevelCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        UrgencyLevel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        UrgencyLevelResponse response = new GetUrgencyLevelByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(UrgencyLevelNotFoundApplicationException.class,
                () -> new GetUrgencyLevelByIdUseCase(repository).execute(UrgencyLevelId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListUrgencyLevelUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        UrgencyLevel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        UrgencyLevelResponse response = new UpdateUrgencyLevelUseCase(repository).execute(
                new UpdateUrgencyLevelCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        UrgencyLevel other = sample();
        assertThrows(UrgencyLevelNotFoundApplicationException.class,
                () -> new UpdateUrgencyLevelUseCase(repository).execute(
                        new UpdateUrgencyLevelCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements UrgencyLevelRepository {
        private UrgencyLevel aggregate;
        private UrgencyLevel deletedAggregate;

        private FakeRepository(UrgencyLevel aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public UrgencyLevel save(UrgencyLevel value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<UrgencyLevel> findById(UrgencyLevelId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<UrgencyLevel> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(UrgencyLevel value) {
            deletedAggregate = value;
        }

        private UrgencyLevel deletedAggregate() {
            return deletedAggregate;
        }
    }
}
