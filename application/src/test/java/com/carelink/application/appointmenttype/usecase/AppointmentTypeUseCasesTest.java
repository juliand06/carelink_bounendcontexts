package com.carelink.application.appointmenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmenttype.command.RegisterAppointmentTypeCommand;
import com.carelink.application.appointmenttype.command.UpdateAppointmentTypeCommand;
import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.application.appointmenttype.exception.AppointmentTypeNotFoundApplicationException;
import com.carelink.domain.appointmenttype.event.AppointmentTypeDeletedEvent;
import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import org.junit.jupiter.api.Test;

class AppointmentTypeUseCasesTest {
    private static AppointmentType sample() {
        return AppointmentType.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentTypeResponse response = new RegisterAppointmentTypeUseCase(repository).execute(
                new RegisterAppointmentTypeCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        AppointmentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentTypeResponse response = new GetAppointmentTypeByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentTypeNotFoundApplicationException.class,
                () -> new GetAppointmentTypeByIdUseCase(repository).execute(AppointmentTypeId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListAppointmentTypeUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        AppointmentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentTypeResponse response = new UpdateAppointmentTypeUseCase(repository).execute(
                new UpdateAppointmentTypeCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentType other = sample();
        assertThrows(AppointmentTypeNotFoundApplicationException.class,
                () -> new UpdateAppointmentTypeUseCase(repository).execute(
                        new UpdateAppointmentTypeCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements AppointmentTypeRepository {
        private AppointmentType aggregate;
        private AppointmentType deletedAggregate;

        private FakeRepository(AppointmentType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public AppointmentType save(AppointmentType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<AppointmentType> findById(AppointmentTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<AppointmentType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(AppointmentType value) {
            deletedAggregate = value;
        }

        private AppointmentType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
