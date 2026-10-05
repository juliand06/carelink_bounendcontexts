package com.carelink.application.llmmodel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmmodel.command.RegisterLlmModelCommand;
import com.carelink.application.llmmodel.command.UpdateLlmModelCommand;
import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.application.llmmodel.exception.LlmModelNotFoundApplicationException;
import com.carelink.domain.llmmodel.event.LlmModelDeletedEvent;
import com.carelink.domain.llmmodel.model.aggregate.LlmModel;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import org.junit.jupiter.api.Test;

class LlmModelUseCasesTest {
    private static LlmModel sample() {
        return LlmModel.register(
                LlmVendorId.generate(),
                "Sample",
                "Sample",
                BigDecimal.ONE,
                BigDecimal.ONE,
                1,
                1,
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmModelResponse response = new RegisterLlmModelUseCase(repository).execute(
                new RegisterLlmModelCommand(
                LlmVendorId.generate(),
                    "Sample",
                    "Sample",
                    BigDecimal.ONE,
                    BigDecimal.ONE,
                    1,
                    1,
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmModel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmModelResponse response = new GetLlmModelByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmModelNotFoundApplicationException.class,
                () -> new GetLlmModelByIdUseCase(repository).execute(LlmModelId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmModelUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmModel aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmModelResponse response = new UpdateLlmModelUseCase(repository).execute(
                new UpdateLlmModelCommand(
                aggregate.id(),
                    LlmVendorId.generate(),
                    "Sample",
                    "Sample",
                    BigDecimal.ONE,
                    BigDecimal.ONE,
                    1,
                    1,
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmModel other = sample();
        assertThrows(LlmModelNotFoundApplicationException.class,
                () -> new UpdateLlmModelUseCase(repository).execute(
                        new UpdateLlmModelCommand(
                        other.id(),
                        LlmVendorId.generate(),
                        "Sample",
                        "Sample",
                        BigDecimal.ONE,
                        BigDecimal.ONE,
                        1,
                        1,
                        true)));
    }

    private static final class FakeRepository implements LlmModelRepository {
        private LlmModel aggregate;
        private LlmModel deletedAggregate;

        private FakeRepository(LlmModel aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmModel save(LlmModel value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmModel> findById(LlmModelId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmModel> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmModel value) {
            deletedAggregate = value;
        }

        private LlmModel deletedAggregate() {
            return deletedAggregate;
        }
    }
}
