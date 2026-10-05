package com.carelink.application.drugadministrationroute.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.drugadministrationroute.exception.DrugAdministrationRouteNotFoundApplicationException;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteDeletedEvent;
import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import org.junit.jupiter.api.Test;

class DeleteDrugAdministrationRouteUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        DrugAdministrationRoute aggregate = DrugAdministrationRoute.register(
                "Sample",
                "Sample",
                true);
        FakeRepository repository = new FakeRepository(aggregate);
        DrugAdministrationRouteDeletedEvent event = new DeleteDrugAdministrationRouteUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DrugAdministrationRouteNotFoundApplicationException.class,
                () -> new DeleteDrugAdministrationRouteUseCase(repository).execute(DrugAdministrationRouteId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements DrugAdministrationRouteRepository {
        private DrugAdministrationRoute aggregate;
        private DrugAdministrationRoute deletedAggregate;

        private FakeRepository(DrugAdministrationRoute aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public DrugAdministrationRoute save(DrugAdministrationRoute value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<DrugAdministrationRoute> findById(DrugAdministrationRouteId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<DrugAdministrationRoute> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(DrugAdministrationRoute value) {
            deletedAggregate = value;
        }

        private DrugAdministrationRoute deletedAggregate() {
            return deletedAggregate;
        }
    }
}
