package com.carelink.application.evaluationinstrumenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.evaluationinstrumenttype.exception.EvaluationInstrumentTypeNotFoundApplicationException;
import com.carelink.domain.evaluationinstrumenttype.event.EvaluationInstrumentTypeDeletedEvent;
import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import org.junit.jupiter.api.Test;

class DeleteEvaluationInstrumentTypeUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        EvaluationInstrumentType aggregate = EvaluationInstrumentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        EvaluationInstrumentTypeDeletedEvent event = new DeleteEvaluationInstrumentTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EvaluationInstrumentTypeNotFoundApplicationException.class,
                () -> new DeleteEvaluationInstrumentTypeUseCase(repository).execute(EvaluationInstrumentTypeId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements EvaluationInstrumentTypeRepository {
        private EvaluationInstrumentType aggregate;
        private EvaluationInstrumentType deletedAggregate;

        private FakeRepository(EvaluationInstrumentType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public EvaluationInstrumentType save(EvaluationInstrumentType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<EvaluationInstrumentType> findById(EvaluationInstrumentTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<EvaluationInstrumentType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(EvaluationInstrumentType value) {
            deletedAggregate = value;
        }

        private EvaluationInstrumentType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
