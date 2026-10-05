package com.carelink.application.mentalstateevaluation.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.mentalstateevaluation.command.RegisterMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.command.UpdateMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.application.mentalstateevaluation.exception.MentalStateEvaluationNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.event.MentalStateEvaluationDeletedEvent;
import com.carelink.domain.mentalstateevaluation.model.aggregate.MentalStateEvaluation;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.mentalstateevaluation.port.repository.MentalStateEvaluationRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class MentalStateEvaluationUseCasesTest {
    private static MentalStateEvaluation sample() {
        return MentalStateEvaluation.register(
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
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        MentalStateEvaluationResponse response = new RegisterMentalStateEvaluationUseCase(repository).execute(
                new RegisterMentalStateEvaluationCommand(
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
                    SpecialistId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        MentalStateEvaluation aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MentalStateEvaluationResponse response = new GetMentalStateEvaluationByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MentalStateEvaluationNotFoundApplicationException.class,
                () -> new GetMentalStateEvaluationByIdUseCase(repository).execute(MentalStateEvaluationId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListMentalStateEvaluationUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        MentalStateEvaluation aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MentalStateEvaluationResponse response = new UpdateMentalStateEvaluationUseCase(repository).execute(
                new UpdateMentalStateEvaluationCommand(
                aggregate.id(),
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
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        MentalStateEvaluation other = sample();
        assertThrows(MentalStateEvaluationNotFoundApplicationException.class,
                () -> new UpdateMentalStateEvaluationUseCase(repository).execute(
                        new UpdateMentalStateEvaluationCommand(
                        other.id(),
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
                        "Sample")));
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
