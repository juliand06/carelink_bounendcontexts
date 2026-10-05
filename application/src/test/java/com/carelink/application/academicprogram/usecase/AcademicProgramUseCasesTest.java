package com.carelink.application.academicprogram.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.academicprogram.command.RegisterAcademicProgramCommand;
import com.carelink.application.academicprogram.command.UpdateAcademicProgramCommand;
import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.application.academicprogram.exception.AcademicProgramNotFoundApplicationException;
import com.carelink.domain.academicprogram.event.AcademicProgramDeletedEvent;
import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import org.junit.jupiter.api.Test;

class AcademicProgramUseCasesTest {
    private static AcademicProgram sample() {
        return AcademicProgram.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        AcademicProgramResponse response = new RegisterAcademicProgramUseCase(repository).execute(
                new RegisterAcademicProgramCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        AcademicProgram aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AcademicProgramResponse response = new GetAcademicProgramByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(AcademicProgramNotFoundApplicationException.class,
                () -> new GetAcademicProgramByIdUseCase(repository).execute(AcademicProgramId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListAcademicProgramUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        AcademicProgram aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        AcademicProgramResponse response = new UpdateAcademicProgramUseCase(repository).execute(
                new UpdateAcademicProgramCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        AcademicProgram other = sample();
        assertThrows(AcademicProgramNotFoundApplicationException.class,
                () -> new UpdateAcademicProgramUseCase(repository).execute(
                        new UpdateAcademicProgramCommand(
                        other.id(),
                        "Sample")));
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
