package com.carelink.application.caregoalstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.caregoalstatus.command.RegisterCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.command.UpdateCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.application.caregoalstatus.exception.CareGoalStatusNotFoundApplicationException;
import com.carelink.domain.caregoalstatus.event.CareGoalStatusDeletedEvent;
import com.carelink.domain.caregoalstatus.model.aggregate.CareGoalStatus;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.caregoalstatus.port.repository.CareGoalStatusRepository;
import org.junit.jupiter.api.Test;

class CareGoalStatusUseCasesTest {
    private static CareGoalStatus sample() {
        return CareGoalStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        CareGoalStatusResponse response = new RegisterCareGoalStatusUseCase(repository).execute(
                new RegisterCareGoalStatusCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        CareGoalStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalStatusResponse response = new GetCareGoalStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CareGoalStatusNotFoundApplicationException.class,
                () -> new GetCareGoalStatusByIdUseCase(repository).execute(CareGoalStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListCareGoalStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        CareGoalStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CareGoalStatusResponse response = new UpdateCareGoalStatusUseCase(repository).execute(
                new UpdateCareGoalStatusCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        CareGoalStatus other = sample();
        assertThrows(CareGoalStatusNotFoundApplicationException.class,
                () -> new UpdateCareGoalStatusUseCase(repository).execute(
                        new UpdateCareGoalStatusCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
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
