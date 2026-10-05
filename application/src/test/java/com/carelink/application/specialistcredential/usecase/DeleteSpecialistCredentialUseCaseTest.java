package com.carelink.application.specialistcredential.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialistcredential.exception.SpecialistCredentialNotFoundApplicationException;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialDeletedEvent;
import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import org.junit.jupiter.api.Test;

class DeleteSpecialistCredentialUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SpecialistCredential aggregate = SpecialistCredential.register(
                AcademicProgramId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                true,
                "Sample",
                CountryId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistCredentialDeletedEvent event = new DeleteSpecialistCredentialUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistCredentialNotFoundApplicationException.class,
                () -> new DeleteSpecialistCredentialUseCase(repository).execute(SpecialistCredentialId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SpecialistCredentialRepository {
        private SpecialistCredential aggregate;
        private SpecialistCredential deletedAggregate;

        private FakeRepository(SpecialistCredential aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SpecialistCredential save(SpecialistCredential value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SpecialistCredential> findById(SpecialistCredentialId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SpecialistCredential> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SpecialistCredential value) {
            deletedAggregate = value;
        }

        private SpecialistCredential deletedAggregate() {
            return deletedAggregate;
        }
    }
}
