package com.carelink.application.supportconversation.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.carelink.application.supportconversation.exception.SupportConversationNotFoundApplicationException;
import com.carelink.domain.supportconversation.event.SupportConversationDeletedEvent;
import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import org.junit.jupiter.api.Test;

class DeleteSupportConversationUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SupportConversation aggregate = SupportConversation.register(
                SupportConversationStatusId.generate(),
                UrgencyLevelId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                true,
                LocalDateTime.of(2026, 1, 10, 8, 0),
                UUID.randomUUID());
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationDeletedEvent event = new DeleteSupportConversationUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportConversationNotFoundApplicationException.class,
                () -> new DeleteSupportConversationUseCase(repository).execute(SupportConversationId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SupportConversationRepository {
        private SupportConversation aggregate;
        private SupportConversation deletedAggregate;

        private FakeRepository(SupportConversation aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SupportConversation save(SupportConversation value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SupportConversation> findById(SupportConversationId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SupportConversation> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SupportConversation value) {
            deletedAggregate = value;
        }

        private SupportConversation deletedAggregate() {
            return deletedAggregate;
        }
    }
}
