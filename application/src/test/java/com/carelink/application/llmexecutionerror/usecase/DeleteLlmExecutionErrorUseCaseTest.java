package com.carelink.application.llmexecutionerror.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionerror.exception.LlmExecutionErrorNotFoundApplicationException;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorDeletedEvent;
import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import org.junit.jupiter.api.Test;

class DeleteLlmExecutionErrorUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmExecutionError aggregate = LlmExecutionError.register(
                LlmExecutionId.generate(),
                "Sample",
                "Sample",
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionErrorDeletedEvent event = new DeleteLlmExecutionErrorUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionErrorNotFoundApplicationException.class,
                () -> new DeleteLlmExecutionErrorUseCase(repository).execute(LlmExecutionErrorId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements LlmExecutionErrorRepository {
        private LlmExecutionError aggregate;
        private LlmExecutionError deletedAggregate;

        private FakeRepository(LlmExecutionError aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmExecutionError save(LlmExecutionError value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmExecutionError> findById(LlmExecutionErrorId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmExecutionError> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmExecutionError value) {
            deletedAggregate = value;
        }

        private LlmExecutionError deletedAggregate() {
            return deletedAggregate;
        }
    }
}
