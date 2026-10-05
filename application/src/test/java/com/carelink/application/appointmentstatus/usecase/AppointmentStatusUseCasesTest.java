package com.carelink.application.appointmentstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmentstatus.command.RegisterAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.command.UpdateAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.application.appointmentstatus.exception.AppointmentStatusNotFoundApplicationException;
import com.carelink.domain.appointmentstatus.event.AppointmentStatusDeletedEvent;
import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import org.junit.jupiter.api.Test;

class AppointmentStatusUseCasesTest {
    private static AppointmentStatus sample() {
        return AppointmentStatus.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentStatusResponse response = new RegisterAppointmentStatusUseCase(repository).execute(
                new RegisterAppointmentStatusCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        AppointmentStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentStatusResponse response = new GetAppointmentStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentStatusNotFoundApplicationException.class,
                () -> new GetAppointmentStatusByIdUseCase(repository).execute(AppointmentStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListAppointmentStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        AppointmentStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentStatusResponse response = new UpdateAppointmentStatusUseCase(repository).execute(
                new UpdateAppointmentStatusCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentStatus other = sample();
        assertThrows(AppointmentStatusNotFoundApplicationException.class,
                () -> new UpdateAppointmentStatusUseCase(repository).execute(
                        new UpdateAppointmentStatusCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements AppointmentStatusRepository {
        private AppointmentStatus aggregate;
        private AppointmentStatus deletedAggregate;

        private FakeRepository(AppointmentStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public AppointmentStatus save(AppointmentStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<AppointmentStatus> findById(AppointmentStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<AppointmentStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(AppointmentStatus value) {
            deletedAggregate = value;
        }

        private AppointmentStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
