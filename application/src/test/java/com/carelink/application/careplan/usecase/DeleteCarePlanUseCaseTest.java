package com.carelink.application.careplan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.careplan.exception.CarePlanNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.event.CarePlanDeletedEvent;
import com.carelink.domain.careplan.model.aggregate.CarePlan;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteCarePlanUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        CarePlan aggregate = CarePlan.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 1, 1),
                CarePlanStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanDeletedEvent event = new DeleteCarePlanUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CarePlanNotFoundApplicationException.class,
                () -> new DeleteCarePlanUseCase(repository).execute(CarePlanId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements CarePlanRepository {
        private CarePlan aggregate;
        private CarePlan deletedAggregate;

        private FakeRepository(CarePlan aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public CarePlan save(CarePlan value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<CarePlan> findById(CarePlanId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<CarePlan> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(CarePlan value) {
            deletedAggregate = value;
        }

        private CarePlan deletedAggregate() {
            return deletedAggregate;
        }
    }
}
