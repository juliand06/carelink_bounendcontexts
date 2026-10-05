package com.carelink.application.handoff.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.handoff.exception.HandoffNotFoundApplicationException;
import com.carelink.domain.handoff.event.HandoffDeletedEvent;
import com.carelink.domain.handoff.model.aggregate.Handoff;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoff.port.repository.HandoffRepository;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class DeleteHandoffUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Handoff aggregate = Handoff.register(
                SupportConversationId.generate(),
                HandoffStatusId.generate(),
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        HandoffDeletedEvent event = new DeleteHandoffUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(HandoffNotFoundApplicationException.class,
                () -> new DeleteHandoffUseCase(repository).execute(HandoffId.generate()));
        assertNull(repository.deletedAggregate());
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
