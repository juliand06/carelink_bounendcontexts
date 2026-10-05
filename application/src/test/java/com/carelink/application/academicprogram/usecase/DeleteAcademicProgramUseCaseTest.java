package com.carelink.application.academicprogram.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.academicprogram.exception.AcademicProgramNotFoundApplicationException;
import com.carelink.domain.academicprogram.event.AcademicProgramDeletedEvent;
import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import org.junit.jupiter.api.Test;

class DeleteAcademicProgramUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        AcademicProgram aggregate = AcademicProgram.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        AcademicProgramDeletedEvent event = new DeleteAcademicProgramUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AcademicProgramNotFoundApplicationException.class,
                () -> new DeleteAcademicProgramUseCase(repository).execute(AcademicProgramId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements AcademicProgramRepository {
        private AcademicProgram aggregate;
        private AcademicProgram deletedAggregate;

        private FakeRepository(AcademicProgram aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public AcademicProgram save(AcademicProgram value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<AcademicProgram> findById(AcademicProgramId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<AcademicProgram> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(AcademicProgram value) {
            deletedAggregate = value;
        }

        private AcademicProgram deletedAggregate() {
            return deletedAggregate;
        }
    }
}
