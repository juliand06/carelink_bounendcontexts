package com.carelink.application.appointment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

class DeleteAppointmentUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Appointment aggregate = Appointment.register(
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
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentDeletedEvent event = new DeleteAppointmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentNotFoundApplicationException.class,
                () -> new DeleteAppointmentUseCase(repository).execute(AppointmentId.generate()));
        assertNull(repository.deletedAggregate());
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
