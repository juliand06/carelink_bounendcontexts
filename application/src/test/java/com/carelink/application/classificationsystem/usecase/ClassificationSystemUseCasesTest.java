package com.carelink.application.classificationsystem.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.classificationsystem.command.RegisterClassificationSystemCommand;
import com.carelink.application.classificationsystem.command.UpdateClassificationSystemCommand;
import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.application.classificationsystem.exception.ClassificationSystemNotFoundApplicationException;
import com.carelink.domain.classificationsystem.event.ClassificationSystemDeletedEvent;
import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import org.junit.jupiter.api.Test;

class ClassificationSystemUseCasesTest {
    private static ClassificationSystem sample() {
        return ClassificationSystem.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ClassificationSystemResponse response = new RegisterClassificationSystemUseCase(repository).execute(
                new RegisterClassificationSystemCommand(
                "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ClassificationSystem aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClassificationSystemResponse response = new GetClassificationSystemByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClassificationSystemNotFoundApplicationException.class,
                () -> new GetClassificationSystemByIdUseCase(repository).execute(ClassificationSystemId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListClassificationSystemUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ClassificationSystem aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClassificationSystemResponse response = new UpdateClassificationSystemUseCase(repository).execute(
                new UpdateClassificationSystemCommand(
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
        ClassificationSystem other = sample();
        assertThrows(ClassificationSystemNotFoundApplicationException.class,
                () -> new UpdateClassificationSystemUseCase(repository).execute(
                        new UpdateClassificationSystemCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true,
                        "Sample")));
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
