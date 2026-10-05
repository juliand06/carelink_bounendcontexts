package com.carelink.application.urgencylevel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.urgencylevel.exception.UrgencyLevelNotFoundApplicationException;
import com.carelink.domain.urgencylevel.event.UrgencyLevelDeletedEvent;
import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.domain.urgencylevel.port.repository.UrgencyLevelRepository;
import org.junit.jupiter.api.Test;

class DeleteUrgencyLevelUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        UrgencyLevel aggregate = UrgencyLevel.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        UrgencyLevelDeletedEvent event = new DeleteUrgencyLevelUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(UrgencyLevelNotFoundApplicationException.class,
                () -> new DeleteUrgencyLevelUseCase(repository).execute(UrgencyLevelId.generate()));
        assertNull(repository.deletedAggregate());
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
