package com.carelink.application.caregoal.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.caregoal.command.RegisterCareGoalCommand;
import com.carelink.application.caregoal.command.UpdateCareGoalCommand;
import com.carelink.application.caregoal.dto.CareGoalResponse;
import com.carelink.application.caregoal.exception.CareGoalNotFoundApplicationException;
import com.carelink.domain.caregoal.event.CareGoalDeletedEvent;
import com.carelink.domain.caregoal.model.aggregate.CareGoal;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoal.port.repository.CareGoalRepository;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import org.junit.jupiter.api.Test;

class CareGoalUseCasesTest {
    private static CareGoal sample() {
        return CareGoal.register(
                CarePlanId.generate(),
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                CareGoalStatusId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        CareGoalResponse response = new RegisterCareGoalUseCase(repository).execute(
                new RegisterCareGoalCommand(
                CarePlanId.generate(),
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    "Sample",
                    CareGoalStatusId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        CareGoal aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalResponse response = new GetCareGoalByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CareGoalNotFoundApplicationException.class,
                () -> new GetCareGoalByIdUseCase(repository).execute(CareGoalId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListCareGoalUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        CareGoal aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalResponse response = new UpdateCareGoalUseCase(repository).execute(
                new UpdateCareGoalCommand(
                aggregate.id(),
                    CarePlanId.generate(),
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    "Sample",
                    CareGoalStatusId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        CareGoal other = sample();
        assertThrows(CareGoalNotFoundApplicationException.class,
                () -> new UpdateCareGoalUseCase(repository).execute(
                        new UpdateCareGoalCommand(
                        other.id(),
                        CarePlanId.generate(),
                        "Sample",
                        LocalDate.of(2000, 1, 1),
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        "Sample",
                        CareGoalStatusId.generate())));
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
