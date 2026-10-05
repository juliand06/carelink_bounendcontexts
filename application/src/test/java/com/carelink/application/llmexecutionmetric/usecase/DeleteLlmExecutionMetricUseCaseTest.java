package com.carelink.application.llmexecutionmetric.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionmetric.exception.LlmExecutionMetricNotFoundApplicationException;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricDeletedEvent;
import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import org.junit.jupiter.api.Test;

class DeleteLlmExecutionMetricUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmExecutionMetric aggregate = LlmExecutionMetric.register(
                LlmExecutionId.generate(),
                1,
                1,
                1,
                BigDecimal.ONE);
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionMetricDeletedEvent event = new DeleteLlmExecutionMetricUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionMetricNotFoundApplicationException.class,
                () -> new DeleteLlmExecutionMetricUseCase(repository).execute(LlmExecutionMetricId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements LlmExecutionMetricRepository {
        private LlmExecutionMetric aggregate;
        private LlmExecutionMetric deletedAggregate;

        private FakeRepository(LlmExecutionMetric aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmExecutionMetric save(LlmExecutionMetric value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmExecutionMetric> findById(LlmExecutionMetricId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmExecutionMetric> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmExecutionMetric value) {
            deletedAggregate = value;
        }

        private LlmExecutionMetric deletedAggregate() {
            return deletedAggregate;
        }
    }
}
