package com.carelink.application.municipality.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.municipality.command.RegisterMunicipalityCommand;
import com.carelink.application.municipality.command.UpdateMunicipalityCommand;
import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.application.municipality.exception.MunicipalityNotFoundApplicationException;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.event.MunicipalityDeletedEvent;
import com.carelink.domain.municipality.model.aggregate.Municipality;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import org.junit.jupiter.api.Test;

class MunicipalityUseCasesTest {
    private static Municipality sample() {
        return Municipality.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                DepartmentId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        MunicipalityResponse response = new RegisterMunicipalityUseCase(repository).execute(
                new RegisterMunicipalityCommand(
                "Sample",
                    "Sample",
                    "Sample",
                    true,
                    DepartmentId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Municipality aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MunicipalityResponse response = new GetMunicipalityByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MunicipalityNotFoundApplicationException.class,
                () -> new GetMunicipalityByIdUseCase(repository).execute(MunicipalityId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListMunicipalityUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Municipality aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MunicipalityResponse response = new UpdateMunicipalityUseCase(repository).execute(
                new UpdateMunicipalityCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    DepartmentId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Municipality other = sample();
        assertThrows(MunicipalityNotFoundApplicationException.class,
                () -> new UpdateMunicipalityUseCase(repository).execute(
                        new UpdateMunicipalityCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true,
                        DepartmentId.generate())));
    }

    private static final class FakeRepository implements MunicipalityRepository {
        private Municipality aggregate;
        private Municipality deletedAggregate;

        private FakeRepository(Municipality aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Municipality save(Municipality value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Municipality> findById(MunicipalityId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Municipality> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Municipality value) {
            deletedAggregate = value;
        }

        private Municipality deletedAggregate() {
            return deletedAggregate;
        }
    }
}
