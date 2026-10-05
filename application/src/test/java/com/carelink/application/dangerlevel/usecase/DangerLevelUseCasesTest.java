package com.carelink.application.dangerlevel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.dangerlevel.command.RegisterDangerLevelCommand;
import com.carelink.application.dangerlevel.command.UpdateDangerLevelCommand;
import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.application.dangerlevel.exception.DangerLevelNotFoundApplicationException;
import com.carelink.domain.dangerlevel.event.DangerLevelDeletedEvent;
import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import org.junit.jupiter.api.Test;

class DangerLevelUseCasesTest {
    private static DangerLevel sample() {
        return DangerLevel.register(
                "Sample",
                "Sample",
                true,
                1);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        DangerLevelResponse response = new RegisterDangerLevelUseCase(repository).execute(
                new RegisterDangerLevelCommand(
                "Sample",
                    "Sample",
                    true,
                    1));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        DangerLevel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DangerLevelResponse response = new GetDangerLevelByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DangerLevelNotFoundApplicationException.class,
                () -> new GetDangerLevelByIdUseCase(repository).execute(DangerLevelId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListDangerLevelUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        DangerLevel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DangerLevelResponse response = new UpdateDangerLevelUseCase(repository).execute(
                new UpdateDangerLevelCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true,
                    1));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        DangerLevel other = sample();
        assertThrows(DangerLevelNotFoundApplicationException.class,
                () -> new UpdateDangerLevelUseCase(repository).execute(
                        new UpdateDangerLevelCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true,
                        1)));
    }

    private static final class FakeRepository implements DangerLevelRepository {
        private DangerLevel aggregate;
        private DangerLevel deletedAggregate;

        private FakeRepository(DangerLevel aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public DangerLevel save(DangerLevel value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<DangerLevel> findById(DangerLevelId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<DangerLevel> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(DangerLevel value) {
            deletedAggregate = value;
        }

        private DangerLevel deletedAggregate() {
            return deletedAggregate;
        }
    }
}
