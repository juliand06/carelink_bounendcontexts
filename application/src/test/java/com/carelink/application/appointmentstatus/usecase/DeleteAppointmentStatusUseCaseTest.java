package com.carelink.application.appointmentstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.appointmentstatus.exception.AppointmentStatusNotFoundApplicationException;
import com.carelink.domain.appointmentstatus.event.AppointmentStatusDeletedEvent;
import com.carelink.domain.appointmentstatus.model.aggregate.AppointmentStatus;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmentstatus.port.repository.AppointmentStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteAppointmentStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        AppointmentStatus aggregate = AppointmentStatus.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        AppointmentStatusDeletedEvent event = new DeleteAppointmentStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AppointmentStatusNotFoundApplicationException.class,
                () -> new DeleteAppointmentStatusUseCase(repository).execute(AppointmentStatusId.generate()));
        assertNull(repository.deletedAggregate());
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
