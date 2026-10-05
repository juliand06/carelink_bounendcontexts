package com.carelink.application.appointmentmodality.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmentmodality.command.RegisterAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.command.UpdateAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.application.appointmentmodality.exception.AppointmentModalityNotFoundApplicationException;
import com.carelink.domain.appointmentmodality.event.AppointmentModalityDeletedEvent;
import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import org.junit.jupiter.api.Test;

class AppointmentModalityUseCasesTest {
    private static AppointmentModality sample() {
        return AppointmentModality.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentModalityResponse response = new RegisterAppointmentModalityUseCase(repository).execute(
                new RegisterAppointmentModalityCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        AppointmentModality aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentModalityResponse response = new GetAppointmentModalityByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentModalityNotFoundApplicationException.class,
                () -> new GetAppointmentModalityByIdUseCase(repository).execute(AppointmentModalityId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListAppointmentModalityUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        AppointmentModality aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentModalityResponse response = new UpdateAppointmentModalityUseCase(repository).execute(
                new UpdateAppointmentModalityCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentModality other = sample();
        assertThrows(AppointmentModalityNotFoundApplicationException.class,
                () -> new UpdateAppointmentModalityUseCase(repository).execute(
                        new UpdateAppointmentModalityCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements AppointmentModalityRepository {
        private AppointmentModality aggregate;
        private AppointmentModality deletedAggregate;

        private FakeRepository(AppointmentModality aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public AppointmentModality save(AppointmentModality value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<AppointmentModality> findById(AppointmentModalityId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<AppointmentModality> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(AppointmentModality value) {
            deletedAggregate = value;
        }

        private AppointmentModality deletedAggregate() {
            return deletedAggregate;
        }
    }
}
