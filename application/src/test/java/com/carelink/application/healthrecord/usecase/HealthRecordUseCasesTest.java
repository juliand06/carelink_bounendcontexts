package com.carelink.application.healthrecord.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.healthrecord.command.RegisterHealthRecordCommand;
import com.carelink.application.healthrecord.command.UpdateHealthRecordCommand;
import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.application.healthrecord.exception.HealthRecordNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.event.HealthRecordDeletedEvent;
import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecord.port.repository.HealthRecordRepository;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class HealthRecordUseCasesTest {
    private static HealthRecord sample() {
        return HealthRecord.register(
                ClientId.generate(),
                "Sample",
                LocalDateTime.of(2026, 1, 10, 8, 0),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                HealthRecordStatusId.generate(),
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HealthRecordResponse response = new RegisterHealthRecordUseCase(repository).execute(
                new RegisterHealthRecordCommand(
                ClientId.generate(),
                    "Sample",
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    HealthRecordStatusId.generate(),
                    SpecialistId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        HealthRecord aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordResponse response = new GetHealthRecordByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HealthRecordNotFoundApplicationException.class,
                () -> new GetHealthRecordByIdUseCase(repository).execute(HealthRecordId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHealthRecordUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        HealthRecord aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordResponse response = new UpdateHealthRecordUseCase(repository).execute(
                new UpdateHealthRecordCommand(
                aggregate.id(),
                    ClientId.generate(),
                    "Sample",
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    HealthRecordStatusId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        HealthRecord other = sample();
        assertThrows(HealthRecordNotFoundApplicationException.class,
                () -> new UpdateHealthRecordUseCase(repository).execute(
                        new UpdateHealthRecordCommand(
                        other.id(),
                        ClientId.generate(),
                        "Sample",
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        HealthRecordStatusId.generate())));
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
