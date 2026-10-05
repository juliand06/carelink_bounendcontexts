package com.carelink.application.informedconsenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.informedconsenttype.exception.InformedConsentTypeNotFoundApplicationException;
import com.carelink.domain.informedconsenttype.event.InformedConsentTypeDeletedEvent;
import com.carelink.domain.informedconsenttype.model.aggregate.InformedConsentType;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.domain.informedconsenttype.port.repository.InformedConsentTypeRepository;
import org.junit.jupiter.api.Test;

class DeleteInformedConsentTypeUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        InformedConsentType aggregate = InformedConsentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        InformedConsentTypeDeletedEvent event = new DeleteInformedConsentTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(InformedConsentTypeNotFoundApplicationException.class,
                () -> new DeleteInformedConsentTypeUseCase(repository).execute(InformedConsentTypeId.generate()));
        assertNull(repository.deletedAggregate());
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
