package com.carelink.application.handoffstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffstatus.exception.HandoffStatusNotFoundApplicationException;
import com.carelink.domain.handoffstatus.event.HandoffStatusDeletedEvent;
import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteHandoffStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        HandoffStatus aggregate = HandoffStatus.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusDeletedEvent event = new DeleteHandoffStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffStatusNotFoundApplicationException.class,
                () -> new DeleteHandoffStatusUseCase(repository).execute(HandoffStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements HandoffStatusRepository {
        private HandoffStatus aggregate;
        private HandoffStatus deletedAggregate;

        private FakeRepository(HandoffStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HandoffStatus save(HandoffStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HandoffStatus> findById(HandoffStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HandoffStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HandoffStatus value) {
            deletedAggregate = value;
        }

        private HandoffStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
