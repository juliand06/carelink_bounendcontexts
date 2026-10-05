package com.carelink.application.iddocumenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.iddocumenttype.exception.IdDocumentTypeNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeDeletedEvent;
import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import org.junit.jupiter.api.Test;

class DeleteIdDocumentTypeUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        IdDocumentType aggregate = IdDocumentType.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        IdDocumentTypeDeletedEvent event = new DeleteIdDocumentTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(IdDocumentTypeNotFoundApplicationException.class,
                () -> new DeleteIdDocumentTypeUseCase(repository).execute(IdDocumentTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements IdDocumentTypeRepository {
        private IdDocumentType aggregate;
        private IdDocumentType deletedAggregate;

        private FakeRepository(IdDocumentType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public IdDocumentType save(IdDocumentType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<IdDocumentType> findById(IdDocumentTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<IdDocumentType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(IdDocumentType value) {
            deletedAggregate = value;
        }

        private IdDocumentType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
