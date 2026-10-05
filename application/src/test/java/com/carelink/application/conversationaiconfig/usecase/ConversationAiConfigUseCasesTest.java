package com.carelink.application.conversationaiconfig.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.conversationaiconfig.command.RegisterConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.command.UpdateConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.application.conversationaiconfig.exception.ConversationAiConfigNotFoundApplicationException;
import com.carelink.domain.conversationaiconfig.event.ConversationAiConfigDeletedEvent;
import com.carelink.domain.conversationaiconfig.model.aggregate.ConversationAiConfig;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.conversationaiconfig.port.repository.ConversationAiConfigRepository;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class ConversationAiConfigUseCasesTest {
    private static ConversationAiConfig sample() {
        return ConversationAiConfig.register(
                SupportConversationId.generate(),
                true,
                LlmModelId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ConversationAiConfigResponse response = new RegisterConversationAiConfigUseCase(repository).execute(
                new RegisterConversationAiConfigCommand(
                SupportConversationId.generate(),
                    true,
                    LlmModelId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ConversationAiConfig aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationAiConfigResponse response = new GetConversationAiConfigByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ConversationAiConfigNotFoundApplicationException.class,
                () -> new GetConversationAiConfigByIdUseCase(repository).execute(ConversationAiConfigId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListConversationAiConfigUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ConversationAiConfig aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationAiConfigResponse response = new UpdateConversationAiConfigUseCase(repository).execute(
                new UpdateConversationAiConfigCommand(
                aggregate.id(),
                    SupportConversationId.generate(),
                    true,
                    LlmModelId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ConversationAiConfig other = sample();
        assertThrows(ConversationAiConfigNotFoundApplicationException.class,
                () -> new UpdateConversationAiConfigUseCase(repository).execute(
                        new UpdateConversationAiConfigCommand(
                        other.id(),
                        SupportConversationId.generate(),
                        true,
                        LlmModelId.generate())));
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
