package com.carelink.application.client.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

class DeleteClientUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Client aggregate = Client.register(
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
        FakeRepository repository = new FakeRepository(aggregate);
        ClientDeletedEvent event = new DeleteClientUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientNotFoundApplicationException.class,
                () -> new DeleteClientUseCase(repository).execute(ClientId.generate()));
        assertNull(repository.deletedAggregate());
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
