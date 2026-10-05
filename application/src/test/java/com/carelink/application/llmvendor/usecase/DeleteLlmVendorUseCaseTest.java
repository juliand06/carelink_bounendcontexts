package com.carelink.application.llmvendor.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmvendor.exception.LlmVendorNotFoundApplicationException;
import com.carelink.domain.llmvendor.event.LlmVendorDeletedEvent;
import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import org.junit.jupiter.api.Test;

class DeleteLlmVendorUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        LlmVendor aggregate = LlmVendor.register(
                "Sample",
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        LlmVendorDeletedEvent event = new DeleteLlmVendorUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmVendorNotFoundApplicationException.class,
                () -> new DeleteLlmVendorUseCase(repository).execute(LlmVendorId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements LlmVendorRepository {
        private LlmVendor aggregate;
        private LlmVendor deletedAggregate;

        private FakeRepository(LlmVendor aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public LlmVendor save(LlmVendor value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<LlmVendor> findById(LlmVendorId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<LlmVendor> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(LlmVendor value) {
            deletedAggregate = value;
        }

        private LlmVendor deletedAggregate() {
            return deletedAggregate;
        }
    }
}
