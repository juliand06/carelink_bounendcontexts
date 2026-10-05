package com.carelink.application.evaluationinstrumenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.evaluationinstrumenttype.command.RegisterEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.command.UpdateEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.application.evaluationinstrumenttype.exception.EvaluationInstrumentTypeNotFoundApplicationException;
import com.carelink.domain.evaluationinstrumenttype.event.EvaluationInstrumentTypeDeletedEvent;
import com.carelink.domain.evaluationinstrumenttype.model.aggregate.EvaluationInstrumentType;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.domain.evaluationinstrumenttype.port.repository.EvaluationInstrumentTypeRepository;
import org.junit.jupiter.api.Test;

class EvaluationInstrumentTypeUseCasesTest {
    private static EvaluationInstrumentType sample() {
        return EvaluationInstrumentType.register(
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        EvaluationInstrumentTypeResponse response = new RegisterEvaluationInstrumentTypeUseCase(repository).execute(
                new RegisterEvaluationInstrumentTypeCommand(
                "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        EvaluationInstrumentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        EvaluationInstrumentTypeResponse response = new GetEvaluationInstrumentTypeByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(EvaluationInstrumentTypeNotFoundApplicationException.class,
                () -> new GetEvaluationInstrumentTypeByIdUseCase(repository).execute(EvaluationInstrumentTypeId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListEvaluationInstrumentTypeUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        EvaluationInstrumentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        EvaluationInstrumentTypeResponse response = new UpdateEvaluationInstrumentTypeUseCase(repository).execute(
                new UpdateEvaluationInstrumentTypeCommand(
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
        EvaluationInstrumentType other = sample();
        assertThrows(EvaluationInstrumentTypeNotFoundApplicationException.class,
                () -> new UpdateEvaluationInstrumentTypeUseCase(repository).execute(
                        new UpdateEvaluationInstrumentTypeCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true,
                        "Sample")));
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
