package com.carelink.application.appointmenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmenttype.exception.AppointmentTypeNotFoundApplicationException;
import com.carelink.domain.appointmenttype.event.AppointmentTypeDeletedEvent;
import com.carelink.domain.appointmenttype.model.aggregate.AppointmentType;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.appointmenttype.port.repository.AppointmentTypeRepository;
import org.junit.jupiter.api.Test;

class DeleteAppointmentTypeUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        AppointmentType aggregate = AppointmentType.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentTypeDeletedEvent event = new DeleteAppointmentTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentTypeNotFoundApplicationException.class,
                () -> new DeleteAppointmentTypeUseCase(repository).execute(AppointmentTypeId.generate()));
        assertNull(repository.deletedAggregate());
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
