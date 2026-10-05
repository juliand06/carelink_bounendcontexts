package com.carelink.application.appointment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointment.command.RegisterAppointmentCommand;
import com.carelink.application.appointment.command.UpdateAppointmentCommand;
import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.application.appointment.exception.AppointmentNotFoundApplicationException;
import com.carelink.domain.appointment.event.AppointmentDeletedEvent;
import com.carelink.domain.appointment.model.aggregate.Appointment;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointment.port.repository.AppointmentRepository;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class AppointmentUseCasesTest {
    private static Appointment sample() {
        return Appointment.register(
                HealthRecordId.generate(),
                SpecialistId.generate(),
                AppointmentTypeId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                "Sample",
                "Sample",
                AppointmentModalityId.generate(),
                AppointmentStatusId.generate(),
                SpecialistId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        AppointmentResponse response = new RegisterAppointmentUseCase(repository).execute(
                new RegisterAppointmentCommand(
                HealthRecordId.generate(),
                    SpecialistId.generate(),
                    AppointmentTypeId.generate(),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    "Sample",
                    "Sample",
                    AppointmentModalityId.generate(),
                    AppointmentStatusId.generate(),
                    SpecialistId.generate(),
                    SpecialistId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Appointment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentResponse response = new GetAppointmentByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentNotFoundApplicationException.class,
                () -> new GetAppointmentByIdUseCase(repository).execute(AppointmentId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListAppointmentUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Appointment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentResponse response = new UpdateAppointmentUseCase(repository).execute(
                new UpdateAppointmentCommand(
                aggregate.id(),
                    HealthRecordId.generate(),
                    SpecialistId.generate(),
                    AppointmentTypeId.generate(),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    "Sample",
                    "Sample",
                    AppointmentModalityId.generate(),
                    AppointmentStatusId.generate(),
                    SpecialistId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Appointment other = sample();
        assertThrows(AppointmentNotFoundApplicationException.class,
                () -> new UpdateAppointmentUseCase(repository).execute(
                        new UpdateAppointmentCommand(
                        other.id(),
                        HealthRecordId.generate(),
                        SpecialistId.generate(),
                        AppointmentTypeId.generate(),
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        "Sample",
                        "Sample",
                        AppointmentModalityId.generate(),
                        AppointmentStatusId.generate(),
                        SpecialistId.generate())));
    }

    private static final class FakeRepository implements AppointmentRepository {
        private Appointment aggregate;
        private Appointment deletedAggregate;

        private FakeRepository(Appointment aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Appointment save(Appointment value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Appointment> findById(AppointmentId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Appointment> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Appointment value) {
            deletedAggregate = value;
        }

        private Appointment deletedAggregate() {
            return deletedAggregate;
        }
    }
}
