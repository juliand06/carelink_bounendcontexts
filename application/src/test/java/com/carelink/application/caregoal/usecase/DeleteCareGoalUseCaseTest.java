package com.carelink.application.caregoal.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.caregoal.exception.CareGoalNotFoundApplicationException;
import com.carelink.domain.caregoal.event.CareGoalDeletedEvent;
import com.carelink.domain.caregoal.model.aggregate.CareGoal;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import org.junit.jupiter.api.Test;

class DeleteCareGoalUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        CareGoal aggregate = CareGoal.register(
                CarePlanId.generate(),
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                CareGoalStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalDeletedEvent event = new DeleteCareGoalUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CareGoalNotFoundApplicationException.class,
                () -> new DeleteCareGoalUseCase(repository).execute(CareGoalId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements CareGoalRepository {
        private CareGoal aggregate;
        private CareGoal deletedAggregate;

        private FakeRepository(CareGoal aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public CareGoal save(CareGoal value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<CareGoal> findById(CareGoalId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<CareGoal> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(CareGoal value) {
            deletedAggregate = value;
        }

        private CareGoal deletedAggregate() {
            return deletedAggregate;
        }
    }
}
