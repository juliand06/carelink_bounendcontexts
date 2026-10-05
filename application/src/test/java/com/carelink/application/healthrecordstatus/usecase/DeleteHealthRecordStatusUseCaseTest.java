package com.carelink.application.healthrecordstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.healthrecordstatus.exception.HealthRecordStatusNotFoundApplicationException;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusDeletedEvent;
import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteHealthRecordStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        HealthRecordStatus aggregate = HealthRecordStatus.register(
                "Sample",
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordStatusDeletedEvent event = new DeleteHealthRecordStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HealthRecordStatusNotFoundApplicationException.class,
                () -> new DeleteHealthRecordStatusUseCase(repository).execute(HealthRecordStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements HealthRecordStatusRepository {
        private HealthRecordStatus aggregate;
        private HealthRecordStatus deletedAggregate;

        private FakeRepository(HealthRecordStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HealthRecordStatus save(HealthRecordStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HealthRecordStatus> findById(HealthRecordStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HealthRecordStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HealthRecordStatus value) {
            deletedAggregate = value;
        }

        private HealthRecordStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
