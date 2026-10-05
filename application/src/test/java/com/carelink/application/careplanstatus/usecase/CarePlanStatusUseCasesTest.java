package com.carelink.application.careplanstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.careplanstatus.command.RegisterCarePlanStatusCommand;
import com.carelink.application.careplanstatus.command.UpdateCarePlanStatusCommand;
import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.application.careplanstatus.exception.CarePlanStatusNotFoundApplicationException;
import com.carelink.domain.careplanstatus.event.CarePlanStatusDeletedEvent;
import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import org.junit.jupiter.api.Test;

class CarePlanStatusUseCasesTest {
    private static CarePlanStatus sample() {
        return CarePlanStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        CarePlanStatusResponse response = new RegisterCarePlanStatusUseCase(repository).execute(
                new RegisterCarePlanStatusCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        CarePlanStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanStatusResponse response = new GetCarePlanStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CarePlanStatusNotFoundApplicationException.class,
                () -> new GetCarePlanStatusByIdUseCase(repository).execute(CarePlanStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListCarePlanStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        CarePlanStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanStatusResponse response = new UpdateCarePlanStatusUseCase(repository).execute(
                new UpdateCarePlanStatusCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        CarePlanStatus other = sample();
        assertThrows(CarePlanStatusNotFoundApplicationException.class,
                () -> new UpdateCarePlanStatusUseCase(repository).execute(
                        new UpdateCarePlanStatusCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements CarePlanStatusRepository {
        private CarePlanStatus aggregate;
        private CarePlanStatus deletedAggregate;

        private FakeRepository(CarePlanStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public CarePlanStatus save(CarePlanStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<CarePlanStatus> findById(CarePlanStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<CarePlanStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(CarePlanStatus value) {
            deletedAggregate = value;
        }

        private CarePlanStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
