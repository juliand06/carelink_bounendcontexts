package com.carelink.application.careplanstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.careplanstatus.exception.CarePlanStatusNotFoundApplicationException;
import com.carelink.domain.careplanstatus.event.CarePlanStatusDeletedEvent;
import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteCarePlanStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        CarePlanStatus aggregate = CarePlanStatus.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanStatusDeletedEvent event = new DeleteCarePlanStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CarePlanStatusNotFoundApplicationException.class,
                () -> new DeleteCarePlanStatusUseCase(repository).execute(CarePlanStatusId.generate()));
        assertNull(repository.deletedAggregate());
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
