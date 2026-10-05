package com.carelink.application.llmexecutionstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionstatus.exception.LlmExecutionStatusNotFoundApplicationException;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusDeletedEvent;
import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import org.junit.jupiter.api.Test;

class DeleteLlmExecutionStatusUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmExecutionStatus aggregate = LlmExecutionStatus.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionStatusDeletedEvent event = new DeleteLlmExecutionStatusUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionStatusNotFoundApplicationException.class,
                () -> new DeleteLlmExecutionStatusUseCase(repository).execute(LlmExecutionStatusId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements LlmExecutionStatusRepository {
        private LlmExecutionStatus aggregate;
        private LlmExecutionStatus deletedAggregate;

        private FakeRepository(LlmExecutionStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmExecutionStatus save(LlmExecutionStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmExecutionStatus> findById(LlmExecutionStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmExecutionStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmExecutionStatus value) {
            deletedAggregate = value;
        }

        private LlmExecutionStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
