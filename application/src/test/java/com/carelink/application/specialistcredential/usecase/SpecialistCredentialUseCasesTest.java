package com.carelink.application.specialistcredential.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialistcredential.command.RegisterSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.command.UpdateSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.application.specialistcredential.exception.SpecialistCredentialNotFoundApplicationException;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialDeletedEvent;
import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.domain.specialistcredential.port.repository.SpecialistCredentialRepository;
import org.junit.jupiter.api.Test;

class SpecialistCredentialUseCasesTest {
    private static SpecialistCredential sample() {
        return SpecialistCredential.register(
                AcademicProgramId.generate(),
                SpecialistId.generate(),
                "Sample",
                "Sample",
                true,
                "Sample",
                CountryId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SpecialistCredentialResponse response = new RegisterSpecialistCredentialUseCase(repository).execute(
                new RegisterSpecialistCredentialCommand(
                AcademicProgramId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    true,
                    "Sample",
                    CountryId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SpecialistCredential aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistCredentialResponse response = new GetSpecialistCredentialByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistCredentialNotFoundApplicationException.class,
                () -> new GetSpecialistCredentialByIdUseCase(repository).execute(SpecialistCredentialId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSpecialistCredentialUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SpecialistCredential aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistCredentialResponse response = new UpdateSpecialistCredentialUseCase(repository).execute(
                new UpdateSpecialistCredentialCommand(
                aggregate.id(),
                    AcademicProgramId.generate(),
                    SpecialistId.generate(),
                    "Sample",
                    "Sample",
                    true,
                    "Sample",
                    CountryId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SpecialistCredential other = sample();
        assertThrows(SpecialistCredentialNotFoundApplicationException.class,
                () -> new UpdateSpecialistCredentialUseCase(repository).execute(
                        new UpdateSpecialistCredentialCommand(
                        other.id(),
                        AcademicProgramId.generate(),
                        SpecialistId.generate(),
                        "Sample",
                        "Sample",
                        true,
                        "Sample",
                        CountryId.generate())));
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
