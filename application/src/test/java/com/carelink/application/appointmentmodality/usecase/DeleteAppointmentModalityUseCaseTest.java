package com.carelink.application.appointmentmodality.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmentmodality.exception.AppointmentModalityNotFoundApplicationException;
import com.carelink.domain.appointmentmodality.event.AppointmentModalityDeletedEvent;
import com.carelink.domain.appointmentmodality.model.aggregate.AppointmentModality;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentmodality.port.repository.AppointmentModalityRepository;
import org.junit.jupiter.api.Test;

class DeleteAppointmentModalityUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        AppointmentModality aggregate = AppointmentModality.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentModalityDeletedEvent event = new DeleteAppointmentModalityUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentModalityNotFoundApplicationException.class,
                () -> new DeleteAppointmentModalityUseCase(repository).execute(AppointmentModalityId.generate()));
        assertNull(repository.deletedAggregate());
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
