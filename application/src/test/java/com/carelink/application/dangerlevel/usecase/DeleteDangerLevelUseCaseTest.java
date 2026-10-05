package com.carelink.application.dangerlevel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.dangerlevel.exception.DangerLevelNotFoundApplicationException;
import com.carelink.domain.dangerlevel.event.DangerLevelDeletedEvent;
import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;
import org.junit.jupiter.api.Test;

class DeleteDangerLevelUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        DangerLevel aggregate = DangerLevel.register(
                "Sample",
                "Sample",
                true,
                1);
        FakeRepository repository = new FakeRepository(aggregate);
        DangerLevelDeletedEvent event = new DeleteDangerLevelUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DangerLevelNotFoundApplicationException.class,
                () -> new DeleteDangerLevelUseCase(repository).execute(DangerLevelId.generate()));
        assertNull(repository.deletedAggregate());
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
