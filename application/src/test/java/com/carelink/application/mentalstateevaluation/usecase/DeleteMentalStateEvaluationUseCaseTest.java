package com.carelink.application.mentalstateevaluation.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.mentalstateevaluation.exception.MentalStateEvaluationNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationDeletedEvent;
import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteMentalStateEvaluationUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        MentalStateEvaluation aggregate = MentalStateEvaluation.register(
                AppointmentId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                SpecialistId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        MentalStateEvaluationDeletedEvent event = new DeleteMentalStateEvaluationUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MentalStateEvaluationNotFoundApplicationException.class,
                () -> new DeleteMentalStateEvaluationUseCase(repository).execute(MentalStateEvaluationId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements MentalStateEvaluationRepository {
        private MentalStateEvaluation aggregate;
        private MentalStateEvaluation deletedAggregate;

        private FakeRepository(MentalStateEvaluation aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public MentalStateEvaluation save(MentalStateEvaluation value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<MentalStateEvaluation> findById(MentalStateEvaluationId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<MentalStateEvaluation> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(MentalStateEvaluation value) {
            deletedAggregate = value;
        }

        private MentalStateEvaluation deletedAggregate() {
            return deletedAggregate;
        }
    }
}
