package com.carelink.application.dangerassessment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.dangerassessment.exception.DangerAssessmentNotFoundApplicationException;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.dangerassessment.event.DangerAssessmentDeletedEvent;
import com.carelink.domain.dangerassessment.model.aggregate.DangerAssessment;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerassessment.port.repository.DangerAssessmentRepository;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteDangerAssessmentUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        DangerAssessment aggregate = DangerAssessment.register(
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
        FakeRepository repository = new FakeRepository(aggregate);
        DangerAssessmentDeletedEvent event = new DeleteDangerAssessmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DangerAssessmentNotFoundApplicationException.class,
                () -> new DeleteDangerAssessmentUseCase(repository).execute(DangerAssessmentId.generate()));
        assertNull(repository.deletedAggregate());
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
