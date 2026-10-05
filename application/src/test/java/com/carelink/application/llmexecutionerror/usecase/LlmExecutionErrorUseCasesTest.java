package com.carelink.application.llmexecutionerror.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionerror.command.RegisterLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.command.UpdateLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.application.llmexecutionerror.exception.LlmExecutionErrorNotFoundApplicationException;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.event.LlmExecutionErrorDeletedEvent;
import com.carelink.domain.llmexecutionerror.model.aggregate.LlmExecutionError;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.domain.llmexecutionerror.port.repository.LlmExecutionErrorRepository;
import org.junit.jupiter.api.Test;

class LlmExecutionErrorUseCasesTest {
    private static LlmExecutionError sample() {
        return LlmExecutionError.register(
                LlmExecutionId.generate(),
                "Sample",
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionErrorResponse response = new RegisterLlmExecutionErrorUseCase(repository).execute(
                new RegisterLlmExecutionErrorCommand(
                LlmExecutionId.generate(),
                    "Sample",
                    "Sample",
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmExecutionError aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionErrorResponse response = new GetLlmExecutionErrorByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionErrorNotFoundApplicationException.class,
                () -> new GetLlmExecutionErrorByIdUseCase(repository).execute(LlmExecutionErrorId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmExecutionErrorUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmExecutionError aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionErrorResponse response = new UpdateLlmExecutionErrorUseCase(repository).execute(
                new UpdateLlmExecutionErrorCommand(
                aggregate.id(),
                    LlmExecutionId.generate(),
                    "Sample",
                    "Sample",
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionError other = sample();
        assertThrows(LlmExecutionErrorNotFoundApplicationException.class,
                () -> new UpdateLlmExecutionErrorUseCase(repository).execute(
                        new UpdateLlmExecutionErrorCommand(
                        other.id(),
                        LlmExecutionId.generate(),
                        "Sample",
                        "Sample",
                        "Sample")));
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
