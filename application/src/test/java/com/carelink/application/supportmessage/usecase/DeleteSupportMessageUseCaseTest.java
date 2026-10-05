package com.carelink.application.supportmessage.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.supportmessage.exception.SupportMessageNotFoundApplicationException;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.event.SupportMessageDeletedEvent;
import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import org.junit.jupiter.api.Test;

class DeleteSupportMessageUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SupportMessage aggregate = SupportMessage.register(
                SupportConversationId.generate(),
                MessageFormatId.generate(),
                ConversationMemberId.generate(),
                "{\"key\":\"value\"}",
                "{\"key\":\"value\"}");
        FakeRepository repository = new FakeRepository(aggregate);
        SupportMessageDeletedEvent event = new DeleteSupportMessageUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportMessageNotFoundApplicationException.class,
                () -> new DeleteSupportMessageUseCase(repository).execute(SupportMessageId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SupportMessageRepository {
        private SupportMessage aggregate;
        private SupportMessage deletedAggregate;

        private FakeRepository(SupportMessage aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SupportMessage save(SupportMessage value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SupportMessage> findById(SupportMessageId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SupportMessage> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SupportMessage value) {
            deletedAggregate = value;
        }

        private SupportMessage deletedAggregate() {
            return deletedAggregate;
        }
    }
}
