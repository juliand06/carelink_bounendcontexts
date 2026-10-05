package com.carelink.application.drugadministrationroute.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.drugadministrationroute.command.RegisterDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.command.UpdateDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.application.drugadministrationroute.exception.DrugAdministrationRouteNotFoundApplicationException;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteDeletedEvent;
import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import org.junit.jupiter.api.Test;

class DrugAdministrationRouteUseCasesTest {
    private static DrugAdministrationRoute sample() {
        return DrugAdministrationRoute.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        DrugAdministrationRouteResponse response = new RegisterDrugAdministrationRouteUseCase(repository).execute(
                new RegisterDrugAdministrationRouteCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        DrugAdministrationRoute aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DrugAdministrationRouteResponse response = new GetDrugAdministrationRouteByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(DrugAdministrationRouteNotFoundApplicationException.class,
                () -> new GetDrugAdministrationRouteByIdUseCase(repository).execute(DrugAdministrationRouteId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListDrugAdministrationRouteUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        DrugAdministrationRoute aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        DrugAdministrationRouteResponse response = new UpdateDrugAdministrationRouteUseCase(repository).execute(
                new UpdateDrugAdministrationRouteCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        DrugAdministrationRoute other = sample();
        assertThrows(DrugAdministrationRouteNotFoundApplicationException.class,
                () -> new UpdateDrugAdministrationRouteUseCase(repository).execute(
                        new UpdateDrugAdministrationRouteCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
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
