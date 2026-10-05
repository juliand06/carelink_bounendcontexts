package com.carelink.application.caregoalstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.caregoalstatus.exception.CareGoalStatusNotFoundApplicationException;
import com.carelink.domain.caregoalstatus.event.CareGoalStatusDeletedEvent;
import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteCareGoalStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        CareGoalStatus aggregate = CareGoalStatus.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalStatusDeletedEvent event = new DeleteCareGoalStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CareGoalStatusNotFoundApplicationException.class,
                () -> new DeleteCareGoalStatusUseCase(repository).execute(CareGoalStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements CareGoalStatusRepository {
        private CareGoalStatus aggregate;
        private CareGoalStatus deletedAggregate;

        private FakeRepository(CareGoalStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public CareGoalStatus save(CareGoalStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<CareGoalStatus> findById(CareGoalStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<CareGoalStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(CareGoalStatus value) {
            deletedAggregate = value;
        }

        private CareGoalStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
