package com.carelink.application.healthrecord.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.healthrecord.exception.HealthRecordNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.event.HealthRecordDeletedEvent;
import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteHealthRecordUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        HealthRecord aggregate = HealthRecord.register(
                ClientId.generate(),
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                HealthRecordStatusId.generate(),
                SpecialistId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordDeletedEvent event = new DeleteHealthRecordUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HealthRecordNotFoundApplicationException.class,
                () -> new DeleteHealthRecordUseCase(repository).execute(HealthRecordId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements HealthRecordRepository {
        private HealthRecord aggregate;
        private HealthRecord deletedAggregate;

        private FakeRepository(HealthRecord aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HealthRecord save(HealthRecord value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HealthRecord> findById(HealthRecordId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HealthRecord> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HealthRecord value) {
            deletedAggregate = value;
        }

        private HealthRecord deletedAggregate() {
            return deletedAggregate;
        }
    }
}
