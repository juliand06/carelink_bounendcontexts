package com.carelink.application.department.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.department.exception.DepartmentNotFoundApplicationException;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.event.DepartmentDeletedEvent;
import com.carelink.domain.department.model.aggregate.Department;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;

class DeleteDepartmentUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Department aggregate = Department.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                CountryId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        DepartmentDeletedEvent event = new DeleteDepartmentUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DepartmentNotFoundApplicationException.class,
                () -> new DeleteDepartmentUseCase(repository).execute(DepartmentId.generate()));
        assertNull(repository.deletedAggregate());
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
