package com.carelink.application.conversationaiconfig.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.conversationaiconfig.exception.ConversationAiConfigNotFoundApplicationException;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigDeletedEvent;
import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class DeleteConversationAiConfigUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ConversationAiConfig aggregate = ConversationAiConfig.register(
                SupportConversationId.generate(),
                true,
                LlmModelId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationAiConfigDeletedEvent event = new DeleteConversationAiConfigUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ConversationAiConfigNotFoundApplicationException.class,
                () -> new DeleteConversationAiConfigUseCase(repository).execute(ConversationAiConfigId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ConversationAiConfigRepository {
        private ConversationAiConfig aggregate;
        private ConversationAiConfig deletedAggregate;

        private FakeRepository(ConversationAiConfig aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ConversationAiConfig save(ConversationAiConfig value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ConversationAiConfig> findById(ConversationAiConfigId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ConversationAiConfig> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ConversationAiConfig value) {
            deletedAggregate = value;
        }

        private ConversationAiConfig deletedAggregate() {
            return deletedAggregate;
        }
    }
}
