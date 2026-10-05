package com.carelink.application.llmexecution.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

class DeleteLlmExecutionUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmExecution aggregate = LlmExecution.register(
                SupportConversationId.generate(),
                SupportMessageId.generate(),
                LlmModelId.generate(),
                LlmExecutionStatusId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionDeletedEvent event = new DeleteLlmExecutionUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionNotFoundApplicationException.class,
                () -> new DeleteLlmExecutionUseCase(repository).execute(LlmExecutionId.generate()));
        assertNull(repository.deletedAggregate());
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
