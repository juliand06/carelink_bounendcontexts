package com.carelink.application.supportconversationstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.supportconversationstatus.exception.SupportConversationStatusNotFoundApplicationException;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusDeletedEvent;
import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteSupportConversationStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SupportConversationStatus aggregate = SupportConversationStatus.register(
                "Sample",
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationStatusDeletedEvent event = new DeleteSupportConversationStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportConversationStatusNotFoundApplicationException.class,
                () -> new DeleteSupportConversationStatusUseCase(repository).execute(SupportConversationStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SupportConversationStatusRepository {
        private SupportConversationStatus aggregate;
        private SupportConversationStatus deletedAggregate;

        private FakeRepository(SupportConversationStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SupportConversationStatus save(SupportConversationStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SupportConversationStatus> findById(SupportConversationStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SupportConversationStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SupportConversationStatus value) {
            deletedAggregate = value;
        }

        private SupportConversationStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
