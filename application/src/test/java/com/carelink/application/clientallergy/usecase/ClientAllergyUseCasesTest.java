package com.carelink.application.clientallergy.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.clientallergy.command.RegisterClientAllergyCommand;
import com.carelink.application.clientallergy.command.UpdateClientAllergyCommand;
import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.application.clientallergy.exception.ClientAllergyNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.event.ClientAllergyDeletedEvent;
import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class ClientAllergyUseCasesTest {
    private static ClientAllergy sample() {
        return ClientAllergy.register(
                ClientId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ClientAllergyResponse response = new RegisterClientAllergyUseCase(repository).execute(
                new RegisterClientAllergyCommand(
                ClientId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    SpecialistId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ClientAllergy aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientAllergyResponse response = new GetClientAllergyByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientAllergyNotFoundApplicationException.class,
                () -> new GetClientAllergyByIdUseCase(repository).execute(ClientAllergyId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListClientAllergyUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ClientAllergy aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientAllergyResponse response = new UpdateClientAllergyUseCase(repository).execute(
                new UpdateClientAllergyCommand(
                aggregate.id(),
                    ClientId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    SpecialistId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ClientAllergy other = sample();
        assertThrows(ClientAllergyNotFoundApplicationException.class,
                () -> new UpdateClientAllergyUseCase(repository).execute(
                        new UpdateClientAllergyCommand(
                        other.id(),
                        ClientId.generate(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true,
                        SpecialistId.generate())));
    }

    private static final class FakeRepository implements ClientAllergyRepository {
        private ClientAllergy aggregate;
        private ClientAllergy deletedAggregate;

        private FakeRepository(ClientAllergy aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ClientAllergy save(ClientAllergy value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ClientAllergy> findById(ClientAllergyId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ClientAllergy> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ClientAllergy value) {
            deletedAggregate = value;
        }

        private ClientAllergy deletedAggregate() {
            return deletedAggregate;
        }
    }
}
