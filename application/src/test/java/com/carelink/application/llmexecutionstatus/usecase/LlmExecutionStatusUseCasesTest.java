package com.carelink.application.llmexecutionstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmexecutionstatus.command.RegisterLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.command.UpdateLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.application.llmexecutionstatus.exception.LlmExecutionStatusNotFoundApplicationException;
import com.carelink.domain.llmexecutionstatus.event.LlmExecutionStatusDeletedEvent;
import com.carelink.domain.llmexecutionstatus.model.aggregate.LlmExecutionStatus;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmexecutionstatus.port.repository.LlmExecutionStatusRepository;
import org.junit.jupiter.api.Test;

class LlmExecutionStatusUseCasesTest {
    private static LlmExecutionStatus sample() {
        return LlmExecutionStatus.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionStatusResponse response = new RegisterLlmExecutionStatusUseCase(repository).execute(
                new RegisterLlmExecutionStatusCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmExecutionStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionStatusResponse response = new GetLlmExecutionStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmExecutionStatusNotFoundApplicationException.class,
                () -> new GetLlmExecutionStatusByIdUseCase(repository).execute(LlmExecutionStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmExecutionStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmExecutionStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmExecutionStatusResponse response = new UpdateLlmExecutionStatusUseCase(repository).execute(
                new UpdateLlmExecutionStatusCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmExecutionStatus other = sample();
        assertThrows(LlmExecutionStatusNotFoundApplicationException.class,
                () -> new UpdateLlmExecutionStatusUseCase(repository).execute(
                        new UpdateLlmExecutionStatusCommand(
                        other.id(),
                        "Sample")));
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
