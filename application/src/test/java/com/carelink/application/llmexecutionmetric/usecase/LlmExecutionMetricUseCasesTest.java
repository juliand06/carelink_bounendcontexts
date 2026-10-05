package com.carelink.application.llmexecutionmetric.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionmetric.command.RegisterLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.command.UpdateLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.application.llmexecutionmetric.exception.LlmExecutionMetricNotFoundApplicationException;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.event.LlmExecutionMetricDeletedEvent;
import com.carelink.domain.llmexecutionmetric.model.aggregate.LlmExecutionMetric;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.domain.llmexecutionmetric.port.repository.LlmExecutionMetricRepository;
import org.junit.jupiter.api.Test;

class LlmExecutionMetricUseCasesTest {
    private static LlmExecutionMetric sample() {
        return LlmExecutionMetric.register(
                LlmExecutionId.generate(),
                1,
                1,
                1,
                BigDecimal.ONE);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionMetricResponse response = new RegisterLlmExecutionMetricUseCase(repository).execute(
                new RegisterLlmExecutionMetricCommand(
                LlmExecutionId.generate(),
                    1,
                    1,
                    1,
                    BigDecimal.ONE));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmExecutionMetric aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionMetricResponse response = new GetLlmExecutionMetricByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionMetricNotFoundApplicationException.class,
                () -> new GetLlmExecutionMetricByIdUseCase(repository).execute(LlmExecutionMetricId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmExecutionMetricUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmExecutionMetric aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionMetricResponse response = new UpdateLlmExecutionMetricUseCase(repository).execute(
                new UpdateLlmExecutionMetricCommand(
                aggregate.id(),
                    LlmExecutionId.generate(),
                    1,
                    1,
                    1,
                    BigDecimal.ONE));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionMetric other = sample();
        assertThrows(LlmExecutionMetricNotFoundApplicationException.class,
                () -> new UpdateLlmExecutionMetricUseCase(repository).execute(
                        new UpdateLlmExecutionMetricCommand(
                        other.id(),
                        LlmExecutionId.generate(),
                        1,
                        1,
                        1,
                        BigDecimal.ONE)));
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
