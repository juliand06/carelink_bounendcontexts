package com.carelink.application.supportmessage.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.supportmessage.command.RegisterSupportMessageCommand;
import com.carelink.application.supportmessage.command.UpdateSupportMessageCommand;
import com.carelink.application.supportmessage.dto.SupportMessageResponse;
import com.carelink.application.supportmessage.exception.SupportMessageNotFoundApplicationException;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.event.SupportMessageDeletedEvent;
import com.carelink.domain.supportmessage.model.aggregate.SupportMessage;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.domain.supportmessage.port.repository.SupportMessageRepository;
import org.junit.jupiter.api.Test;

class SupportMessageUseCasesTest {
    private static SupportMessage sample() {
        return SupportMessage.register(
                SupportConversationId.generate(),
                MessageFormatId.generate(),
                ConversationMemberId.generate(),
                "{\"key\":\"value\"}",
                "{\"key\":\"value\"}");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SupportMessageResponse response = new RegisterSupportMessageUseCase(repository).execute(
                new RegisterSupportMessageCommand(
                SupportConversationId.generate(),
                    MessageFormatId.generate(),
                    ConversationMemberId.generate(),
                    "{\"key\":\"value\"}",
                    "{\"key\":\"value\"}"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SupportMessage aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportMessageResponse response = new GetSupportMessageByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportMessageNotFoundApplicationException.class,
                () -> new GetSupportMessageByIdUseCase(repository).execute(SupportMessageId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSupportMessageUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SupportMessage aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportMessageResponse response = new UpdateSupportMessageUseCase(repository).execute(
                new UpdateSupportMessageCommand(
                aggregate.id(),
                    SupportConversationId.generate(),
                    MessageFormatId.generate(),
                    ConversationMemberId.generate(),
                    "{\"key\":\"value\"}",
                    "{\"key\":\"value\"}"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SupportMessage other = sample();
        assertThrows(SupportMessageNotFoundApplicationException.class,
                () -> new UpdateSupportMessageUseCase(repository).execute(
                        new UpdateSupportMessageCommand(
                        other.id(),
                        SupportConversationId.generate(),
                        MessageFormatId.generate(),
                        ConversationMemberId.generate(),
                        "{\"key\":\"value\"}",
                        "{\"key\":\"value\"}")));
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
