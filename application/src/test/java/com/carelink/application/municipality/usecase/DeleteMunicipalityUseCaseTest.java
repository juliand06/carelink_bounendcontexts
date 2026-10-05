package com.carelink.application.municipality.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.municipality.exception.MunicipalityNotFoundApplicationException;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.event.MunicipalityDeletedEvent;
import com.carelink.domain.municipality.model.aggregate.Municipality;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import org.junit.jupiter.api.Test;

class DeleteMunicipalityUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Municipality aggregate = Municipality.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                DepartmentId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        MunicipalityDeletedEvent event = new DeleteMunicipalityUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MunicipalityNotFoundApplicationException.class,
                () -> new DeleteMunicipalityUseCase(repository).execute(MunicipalityId.generate()));
        assertNull(repository.deletedAggregate());
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
