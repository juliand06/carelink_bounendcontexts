package com.carelink.application.specialist.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialist.command.RegisterSpecialistCommand;
import com.carelink.application.specialist.command.UpdateSpecialistCommand;
import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.application.specialist.exception.SpecialistNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.event.SpecialistDeletedEvent;
import com.carelink.domain.specialist.model.aggregate.Specialist;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import org.junit.jupiter.api.Test;

class SpecialistUseCasesTest {
    private static Specialist sample() {
        return Specialist.register(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                SpecialistRoleId.generate(),
                "Sample",
                true,
                MunicipalityId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SpecialistResponse response = new RegisterSpecialistUseCase(repository).execute(
                new RegisterSpecialistCommand(
                IdDocumentTypeId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    SpecialistRoleId.generate(),
                    "Sample",
                    true,
                    MunicipalityId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Specialist aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistResponse response = new GetSpecialistByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistNotFoundApplicationException.class,
                () -> new GetSpecialistByIdUseCase(repository).execute(SpecialistId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSpecialistUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Specialist aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistResponse response = new UpdateSpecialistUseCase(repository).execute(
                new UpdateSpecialistCommand(
                aggregate.id(),
                    IdDocumentTypeId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    SpecialistRoleId.generate(),
                    "Sample",
                    true,
                    MunicipalityId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Specialist other = sample();
        assertThrows(SpecialistNotFoundApplicationException.class,
                () -> new UpdateSpecialistUseCase(repository).execute(
                        new UpdateSpecialistCommand(
                        other.id(),
                        IdDocumentTypeId.generate(),
                        "Sample",
                        "Sample",
                        "Sample",
                        SpecialistRoleId.generate(),
                        "Sample",
                        true,
                        MunicipalityId.generate())));
    }

    private static final class FakeRepository implements SpecialistRepository {
        private Specialist aggregate;
        private Specialist deletedAggregate;

        private FakeRepository(Specialist aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Specialist save(Specialist value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Specialist> findById(SpecialistId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Specialist> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Specialist value) {
            deletedAggregate = value;
        }

        private Specialist deletedAggregate() {
            return deletedAggregate;
        }
    }
}
