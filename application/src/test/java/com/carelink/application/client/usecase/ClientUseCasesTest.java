package com.carelink.application.client.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.client.command.RegisterClientCommand;
import com.carelink.application.client.command.UpdateClientCommand;
import com.carelink.application.client.dto.ClientResponse;
import com.carelink.application.client.exception.ClientNotFoundApplicationException;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.client.event.ClientDeletedEvent;
import com.carelink.domain.client.model.aggregate.Client;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.client.port.repository.ClientRepository;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class ClientUseCasesTest {
    private static Client sample() {
        return Client.register(
                IdDocumentTypeId.generate(),
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                "Sample",
                LocalDate.of(2000, 1, 1),
                BiologicalSexId.generate(),
                BiologicalSexId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate(),
                SpecialistId.generate(),
                MunicipalityId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ClientResponse response = new RegisterClientUseCase(repository).execute(
                new RegisterClientCommand(
                IdDocumentTypeId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    BiologicalSexId.generate(),
                    BiologicalSexId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    SpecialistId.generate(),
                    SpecialistId.generate(),
                    MunicipalityId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Client aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientResponse response = new GetClientByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientNotFoundApplicationException.class,
                () -> new GetClientByIdUseCase(repository).execute(ClientId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListClientUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Client aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientResponse response = new UpdateClientUseCase(repository).execute(
                new UpdateClientCommand(
                aggregate.id(),
                    IdDocumentTypeId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    "Sample",
                    LocalDate.of(2000, 1, 1),
                    BiologicalSexId.generate(),
                    BiologicalSexId.generate(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    SpecialistId.generate(),
                    MunicipalityId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Client other = sample();
        assertThrows(ClientNotFoundApplicationException.class,
                () -> new UpdateClientUseCase(repository).execute(
                        new UpdateClientCommand(
                        other.id(),
                        IdDocumentTypeId.generate(),
                        "Sample",
                        "Sample",
                        "Sample",
                        "Sample",
                        "Sample",
                        LocalDate.of(2000, 1, 1),
                        BiologicalSexId.generate(),
                        BiologicalSexId.generate(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true,
                        SpecialistId.generate(),
                        MunicipalityId.generate())));
    }

    private static final class FakeRepository implements ClientRepository {
        private Client aggregate;
        private Client deletedAggregate;

        private FakeRepository(Client aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Client save(Client value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Client> findById(ClientId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Client> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Client value) {
            deletedAggregate = value;
        }

        private Client deletedAggregate() {
            return deletedAggregate;
        }
    }
}
