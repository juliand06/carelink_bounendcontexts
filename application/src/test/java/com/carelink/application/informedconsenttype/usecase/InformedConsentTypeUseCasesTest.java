package com.carelink.application.informedconsenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.informedconsenttype.command.RegisterInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.command.UpdateInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.application.informedconsenttype.exception.InformedConsentTypeNotFoundApplicationException;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeDeletedEvent;
import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import org.junit.jupiter.api.Test;

class InformedConsentTypeUseCasesTest {
    private static InformedConsentType sample() {
        return InformedConsentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        InformedConsentTypeResponse response = new RegisterInformedConsentTypeUseCase(repository).execute(
                new RegisterInformedConsentTypeCommand(
                "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        InformedConsentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        InformedConsentTypeResponse response = new GetInformedConsentTypeByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(InformedConsentTypeNotFoundApplicationException.class,
                () -> new GetInformedConsentTypeByIdUseCase(repository).execute(InformedConsentTypeId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListInformedConsentTypeUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        InformedConsentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        InformedConsentTypeResponse response = new UpdateInformedConsentTypeUseCase(repository).execute(
                new UpdateInformedConsentTypeCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        InformedConsentType other = sample();
        assertThrows(InformedConsentTypeNotFoundApplicationException.class,
                () -> new UpdateInformedConsentTypeUseCase(repository).execute(
                        new UpdateInformedConsentTypeCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true,
                        "Sample")));
    }

    private static final class FakeRepository implements InformedConsentTypeRepository {
        private InformedConsentType aggregate;
        private InformedConsentType deletedAggregate;

        private FakeRepository(InformedConsentType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public InformedConsentType save(InformedConsentType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<InformedConsentType> findById(InformedConsentTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<InformedConsentType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(InformedConsentType value) {
            deletedAggregate = value;
        }

        private InformedConsentType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
