package com.carelink.application.classificationsystem.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.classificationsystem.exception.ClassificationSystemNotFoundApplicationException;
import com.carelink.domain.classificationsystem.event.ClassificationSystemDeletedEvent;
import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import org.junit.jupiter.api.Test;

class DeleteClassificationSystemUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ClassificationSystem aggregate = ClassificationSystem.register(
                "Sample",
                "Sample",
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        ClassificationSystemDeletedEvent event = new DeleteClassificationSystemUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClassificationSystemNotFoundApplicationException.class,
                () -> new DeleteClassificationSystemUseCase(repository).execute(ClassificationSystemId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ClassificationSystemRepository {
        private ClassificationSystem aggregate;
        private ClassificationSystem deletedAggregate;

        private FakeRepository(ClassificationSystem aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ClassificationSystem save(ClassificationSystem value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ClassificationSystem> findById(ClassificationSystemId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ClassificationSystem> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ClassificationSystem value) {
            deletedAggregate = value;
        }

        private ClassificationSystem deletedAggregate() {
            return deletedAggregate;
        }
    }
}
