package com.carelink.application.llmmodel.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmmodel.exception.LlmModelNotFoundApplicationException;
import com.carelink.domain.llmmodel.event.LlmModelDeletedEvent;
import com.carelink.domain.llmmodel.model.aggregate.LlmModel;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmmodel.port.repository.LlmModelRepository;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import org.junit.jupiter.api.Test;

class DeleteLlmModelUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmModel aggregate = LlmModel.register(
                LlmVendorId.generate(),
                "Sample",
                "Sample",
                BigDecimal.ONE,
                BigDecimal.ONE,
                1,
                1,
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        LlmModelDeletedEvent event = new DeleteLlmModelUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmModelNotFoundApplicationException.class,
                () -> new DeleteLlmModelUseCase(repository).execute(LlmModelId.generate()));
        assertNull(repository.deletedAggregate());
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
