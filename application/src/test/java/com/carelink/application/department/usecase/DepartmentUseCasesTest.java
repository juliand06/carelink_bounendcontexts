package com.carelink.application.department.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.department.command.RegisterDepartmentCommand;
import com.carelink.application.department.command.UpdateDepartmentCommand;
import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.application.department.exception.DepartmentNotFoundApplicationException;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.event.DepartmentDeletedEvent;
import com.carelink.domain.department.model.aggregate.Department;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;

class DepartmentUseCasesTest {
    private static Department sample() {
        return Department.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                CountryId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        DepartmentResponse response = new RegisterDepartmentUseCase(repository).execute(
                new RegisterDepartmentCommand(
                "Sample",
                    "Sample",
                    "Sample",
                    true,
                    CountryId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Department aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DepartmentResponse response = new GetDepartmentByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DepartmentNotFoundApplicationException.class,
                () -> new GetDepartmentByIdUseCase(repository).execute(DepartmentId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListDepartmentUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Department aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DepartmentResponse response = new UpdateDepartmentUseCase(repository).execute(
                new UpdateDepartmentCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    CountryId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Department other = sample();
        assertThrows(DepartmentNotFoundApplicationException.class,
                () -> new UpdateDepartmentUseCase(repository).execute(
                        new UpdateDepartmentCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true,
                        CountryId.generate())));
    }

    private static final class FakeRepository implements DepartmentRepository {
        private Department aggregate;
        private Department deletedAggregate;

        private FakeRepository(Department aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Department save(Department value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Department> findById(DepartmentId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Department> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Department value) {
            deletedAggregate = value;
        }

        private Department deletedAggregate() {
            return deletedAggregate;
        }
    }
}
