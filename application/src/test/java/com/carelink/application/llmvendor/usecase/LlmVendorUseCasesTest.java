package com.carelink.application.llmvendor.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.llmvendor.command.RegisterLlmVendorCommand;
import com.carelink.application.llmvendor.command.UpdateLlmVendorCommand;
import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.application.llmvendor.exception.LlmVendorNotFoundApplicationException;
import com.carelink.domain.llmvendor.event.LlmVendorDeletedEvent;
import com.carelink.domain.llmvendor.model.aggregate.LlmVendor;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.domain.llmvendor.port.repository.LlmVendorRepository;
import org.junit.jupiter.api.Test;

class LlmVendorUseCasesTest {
    private static LlmVendor sample() {
        return LlmVendor.register(
                "Sample",
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        LlmVendorResponse response = new RegisterLlmVendorUseCase(repository).execute(
                new RegisterLlmVendorCommand(
                "Sample",
                    "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        LlmVendor aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmVendorResponse response = new GetLlmVendorByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(LlmVendorNotFoundApplicationException.class,
                () -> new GetLlmVendorByIdUseCase(repository).execute(LlmVendorId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListLlmVendorUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        LlmVendor aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        LlmVendorResponse response = new UpdateLlmVendorUseCase(repository).execute(
                new UpdateLlmVendorCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        LlmVendor other = sample();
        assertThrows(LlmVendorNotFoundApplicationException.class,
                () -> new UpdateLlmVendorUseCase(repository).execute(
                        new UpdateLlmVendorCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true)));
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
