package com.carelink.application.handoff.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoff.command.RegisterHandoffCommand;
import com.carelink.application.handoff.command.UpdateHandoffCommand;
import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.application.handoff.exception.HandoffNotFoundApplicationException;
import com.carelink.domain.handoff.event.HandoffDeletedEvent;
import com.carelink.domain.handoff.model.aggregate.Handoff;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class HandoffUseCasesTest {
    private static Handoff sample() {
        return Handoff.register(
                SupportConversationId.generate(),
                HandoffStatusId.generate(),
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        HandoffResponse response = new RegisterHandoffUseCase(repository).execute(
                new RegisterHandoffCommand(
                SupportConversationId.generate(),
                    HandoffStatusId.generate(),
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Handoff aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffResponse response = new GetHandoffByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffNotFoundApplicationException.class,
                () -> new GetHandoffByIdUseCase(repository).execute(HandoffId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListHandoffUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Handoff aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffResponse response = new UpdateHandoffUseCase(repository).execute(
                new UpdateHandoffCommand(
                aggregate.id(),
                    SupportConversationId.generate(),
                    HandoffStatusId.generate(),
                    true,
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Handoff other = sample();
        assertThrows(HandoffNotFoundApplicationException.class,
                () -> new UpdateHandoffUseCase(repository).execute(
                        new UpdateHandoffCommand(
                        other.id(),
                        SupportConversationId.generate(),
                        HandoffStatusId.generate(),
                        true,
                        "Sample")));
    }

    private static final class FakeRepository implements HandoffRepository {
        private Handoff aggregate;
        private Handoff deletedAggregate;

        private FakeRepository(Handoff aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Handoff save(Handoff value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Handoff> findById(HandoffId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Handoff> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Handoff value) {
            deletedAggregate = value;
        }

        private Handoff deletedAggregate() {
            return deletedAggregate;
        }
    }
}
