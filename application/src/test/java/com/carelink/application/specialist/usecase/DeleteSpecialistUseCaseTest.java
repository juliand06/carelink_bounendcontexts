package com.carelink.application.specialist.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialist.exception.SpecialistNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.event.SpecialistDeletedEvent;
import com.carelink.domain.specialist.model.aggregate.Specialist;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialist.port.repository.SpecialistRepository;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import org.junit.jupiter.api.Test;

class DeleteSpecialistUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Specialist aggregate = Specialist.register(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                SpecialistRoleId.generate(),
                "Sample",
                true,
                MunicipalityId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistDeletedEvent event = new DeleteSpecialistUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistNotFoundApplicationException.class,
                () -> new DeleteSpecialistUseCase(repository).execute(SpecialistId.generate()));
        assertNull(repository.deletedAggregate());
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
