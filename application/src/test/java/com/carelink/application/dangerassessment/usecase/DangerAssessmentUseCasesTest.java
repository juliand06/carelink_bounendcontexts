package com.carelink.application.dangerassessment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.dangerassessment.command.RegisterDangerAssessmentCommand;
import com.carelink.application.dangerassessment.command.UpdateDangerAssessmentCommand;
import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.application.dangerassessment.exception.DangerAssessmentNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.dangerassessment.event.DangerAssessmentDeletedEvent;
import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DangerAssessmentUseCasesTest {
    private static DangerAssessment sample() {
        return DangerAssessment.register(
                AppointmentId.generate(),
                DangerLevelId.generate(),
                true,
                true,
                true,
                true,
                true,
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        DangerAssessmentResponse response = new RegisterDangerAssessmentUseCase(repository).execute(
                new RegisterDangerAssessmentCommand(
                AppointmentId.generate(),
                    DangerLevelId.generate(),
                    true,
                    true,
                    true,
                    true,
                    true,
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
        DangerAssessment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DangerAssessmentResponse response = new GetDangerAssessmentByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DangerAssessmentNotFoundApplicationException.class,
                () -> new GetDangerAssessmentByIdUseCase(repository).execute(DangerAssessmentId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListDangerAssessmentUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        DangerAssessment aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DangerAssessmentResponse response = new UpdateDangerAssessmentUseCase(repository).execute(
                new UpdateDangerAssessmentCommand(
                aggregate.id(),
                    AppointmentId.generate(),
                    DangerLevelId.generate(),
                    true,
                    true,
                    true,
                    true,
                    true,
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    SpecialistId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        DangerAssessment other = sample();
        assertThrows(DangerAssessmentNotFoundApplicationException.class,
                () -> new UpdateDangerAssessmentUseCase(repository).execute(
                        new UpdateDangerAssessmentCommand(
                        other.id(),
                        AppointmentId.generate(),
                        DangerLevelId.generate(),
                        true,
                        true,
                        true,
                        true,
                        true,
                        "Sample",
                        "Sample",
                        "Sample",
                        "Sample",
                        SpecialistId.generate())));
    }

    private static final class FakeRepository implements DangerAssessmentRepository {
        private DangerAssessment aggregate;
        private DangerAssessment deletedAggregate;

        private FakeRepository(DangerAssessment aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public DangerAssessment save(DangerAssessment value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<DangerAssessment> findById(DangerAssessmentId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<DangerAssessment> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(DangerAssessment value) {
            deletedAggregate = value;
        }

        private DangerAssessment deletedAggregate() {
            return deletedAggregate;
        }
    }
}
