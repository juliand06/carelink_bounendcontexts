package com.carelink.application.handoffstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoffstatus.command.RegisterHandoffStatusCommand;
import com.carelink.application.handoffstatus.command.UpdateHandoffStatusCommand;
import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.application.handoffstatus.exception.HandoffStatusNotFoundApplicationException;
import com.carelink.domain.handoffstatus.event.HandoffStatusDeletedEvent;
import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatus.port.repository.HandoffStatusRepository;
import org.junit.jupiter.api.Test;

class HandoffStatusUseCasesTest {
    private static HandoffStatus sample() {
        return HandoffStatus.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HandoffStatusResponse response = new RegisterHandoffStatusUseCase(repository).execute(
                new RegisterHandoffStatusCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        HandoffStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusResponse response = new GetHandoffStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffStatusNotFoundApplicationException.class,
                () -> new GetHandoffStatusByIdUseCase(repository).execute(HandoffStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHandoffStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        HandoffStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffStatusResponse response = new UpdateHandoffStatusUseCase(repository).execute(
                new UpdateHandoffStatusCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        HandoffStatus other = sample();
        assertThrows(HandoffStatusNotFoundApplicationException.class,
                () -> new UpdateHandoffStatusUseCase(repository).execute(
                        new UpdateHandoffStatusCommand(
                        other.id(),
                        "Sample")));
    }

    private static final class FakeRepository implements HandoffStatusRepository {
        private HandoffStatus aggregate;
        private HandoffStatus deletedAggregate;

        private FakeRepository(HandoffStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public HandoffStatus save(HandoffStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<HandoffStatus> findById(HandoffStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<HandoffStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(HandoffStatus value) {
            deletedAggregate = value;
        }

        private HandoffStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
