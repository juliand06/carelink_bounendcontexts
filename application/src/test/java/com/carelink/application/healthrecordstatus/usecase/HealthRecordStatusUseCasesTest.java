package com.carelink.application.healthrecordstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.healthrecordstatus.command.RegisterHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.command.UpdateHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.application.healthrecordstatus.exception.HealthRecordStatusNotFoundApplicationException;
import com.carelink.domain.healthrecordstatus.event.HealthRecordStatusDeletedEvent;
import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.healthrecordstatus.port.repository.HealthRecordStatusRepository;
import org.junit.jupiter.api.Test;

class HealthRecordStatusUseCasesTest {
    private static HealthRecordStatus sample() {
        return HealthRecordStatus.register(
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HealthRecordStatusResponse response = new RegisterHealthRecordStatusUseCase(repository).execute(
                new RegisterHealthRecordStatusCommand(
                "Sample",
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        HealthRecordStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordStatusResponse response = new GetHealthRecordStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HealthRecordStatusNotFoundApplicationException.class,
                () -> new GetHealthRecordStatusByIdUseCase(repository).execute(HealthRecordStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHealthRecordStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        HealthRecordStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HealthRecordStatusResponse response = new UpdateHealthRecordStatusUseCase(repository).execute(
                new UpdateHealthRecordStatusCommand(
                aggregate.id(),
                    "Sample",
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        HealthRecordStatus other = sample();
        assertThrows(HealthRecordStatusNotFoundApplicationException.class,
                () -> new UpdateHealthRecordStatusUseCase(repository).execute(
                        new UpdateHealthRecordStatusCommand(
                        other.id(),
                        "Sample",
                        "Sample")));
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
