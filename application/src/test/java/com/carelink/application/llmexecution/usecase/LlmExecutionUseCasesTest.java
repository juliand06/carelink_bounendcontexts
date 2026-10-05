package com.carelink.application.llmexecution.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecution.command.RegisterLlmExecutionCommand;
import com.carelink.application.llmexecution.command.UpdateLlmExecutionCommand;
import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.application.llmexecution.exception.LlmExecutionNotFoundApplicationException;
import com.carelink.domain.llmexecution.event.LlmExecutionDeletedEvent;
import com.carelink.domain.llmexecution.model.aggregate.LlmExecution;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecution.port.repository.LlmExecutionRepository;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import org.junit.jupiter.api.Test;

class LlmExecutionUseCasesTest {
    private static LlmExecution sample() {
        return LlmExecution.register(
                SupportConversationId.generate(),
                SupportMessageId.generate(),
                LlmModelId.generate(),
                LlmExecutionStatusId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionResponse response = new RegisterLlmExecutionUseCase(repository).execute(
                new RegisterLlmExecutionCommand(
                SupportConversationId.generate(),
                    SupportMessageId.generate(),
                    LlmModelId.generate(),
                    LlmExecutionStatusId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmExecution aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionResponse response = new GetLlmExecutionByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionNotFoundApplicationException.class,
                () -> new GetLlmExecutionByIdUseCase(repository).execute(LlmExecutionId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmExecutionUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmExecution aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionResponse response = new UpdateLlmExecutionUseCase(repository).execute(
                new UpdateLlmExecutionCommand(
                aggregate.id(),
                    SupportConversationId.generate(),
                    SupportMessageId.generate(),
                    LlmModelId.generate(),
                    LlmExecutionStatusId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecution other = sample();
        assertThrows(LlmExecutionNotFoundApplicationException.class,
                () -> new UpdateLlmExecutionUseCase(repository).execute(
                        new UpdateLlmExecutionCommand(
                        other.id(),
                        SupportConversationId.generate(),
                        SupportMessageId.generate(),
                        LlmModelId.generate(),
                        LlmExecutionStatusId.generate())));
    }

    private static final class FakeRepository implements LlmExecutionRepository {
        private LlmExecution aggregate;
        private LlmExecution deletedAggregate;

        private FakeRepository(LlmExecution aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmExecution save(LlmExecution value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmExecution> findById(LlmExecutionId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmExecution> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmExecution value) {
            deletedAggregate = value;
        }

        private LlmExecution deletedAggregate() {
            return deletedAggregate;
        }
    }
}
