package com.carelink.application.biologicalsex.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.biologicalsex.exception.BiologicalSexNotFoundApplicationException;
import com.carelink.domain.biologicalsex.event.BiologicalSexDeletedEvent;
import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import org.junit.jupiter.api.Test;

class DeleteBiologicalSexUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        BiologicalSex aggregate = BiologicalSex.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        BiologicalSexDeletedEvent event = new DeleteBiologicalSexUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(BiologicalSexNotFoundApplicationException.class,
                () -> new DeleteBiologicalSexUseCase(repository).execute(BiologicalSexId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements BiologicalSexRepository {
        private BiologicalSex aggregate;
        private BiologicalSex deletedAggregate;

        private FakeRepository(BiologicalSex aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public BiologicalSex save(BiologicalSex value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<BiologicalSex> findById(BiologicalSexId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<BiologicalSex> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(BiologicalSex value) {
            deletedAggregate = value;
        }

        private BiologicalSex deletedAggregate() {
            return deletedAggregate;
        }
    }
}
