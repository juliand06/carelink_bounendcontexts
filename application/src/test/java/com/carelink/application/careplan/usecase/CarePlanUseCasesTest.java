package com.carelink.application.careplan.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.careplan.command.RegisterCarePlanCommand;
import com.carelink.application.careplan.command.UpdateCarePlanCommand;
import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.application.careplan.exception.CarePlanNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.event.CarePlanDeletedEvent;
import com.carelink.domain.careplan.model.aggregate.CarePlan;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class CarePlanUseCasesTest {
    private static CarePlan sample() {
        return CarePlan.register(
                AppointmentId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                LocalDate.of(2000, 1, 1),
                CarePlanStatusId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        CarePlanResponse response = new RegisterCarePlanUseCase(repository).execute(
                new RegisterCarePlanCommand(
                AppointmentId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    LocalDate.of(2000, 1, 1),
                    CarePlanStatusId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        CarePlan aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanResponse response = new GetCarePlanByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CarePlanNotFoundApplicationException.class,
                () -> new GetCarePlanByIdUseCase(repository).execute(CarePlanId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListCarePlanUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        CarePlan aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CarePlanResponse response = new UpdateCarePlanUseCase(repository).execute(
                new UpdateCarePlanCommand(
                aggregate.id(),
                    AppointmentId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    LocalDate.of(2000, 1, 1),
                    CarePlanStatusId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        CarePlan other = sample();
        assertThrows(CarePlanNotFoundApplicationException.class,
                () -> new UpdateCarePlanUseCase(repository).execute(
                        new UpdateCarePlanCommand(
                        other.id(),
                        AppointmentId.generate(),
                        SpecialistId.generate(),
                        "Sample",
                        "Sample",
                        LocalDate.of(2000, 1, 1),
                        LocalDate.of(2000, 1, 1),
                        CarePlanStatusId.generate())));
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
