package com.carelink.application.clienttrustedcontact.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.clienttrustedcontact.command.RegisterClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.command.UpdateClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.application.clienttrustedcontact.exception.ClientTrustedContactNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactDeletedEvent;
import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ClientTrustedContactUseCasesTest {
    private static ClientTrustedContact sample() {
        return ClientTrustedContact.register(
                ClientId.generate(),
                TrustedContactId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ClientTrustedContactResponse response = new RegisterClientTrustedContactUseCase(repository).execute(
                new RegisterClientTrustedContactCommand(
                ClientId.generate(),
                    TrustedContactId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ClientTrustedContact aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientTrustedContactResponse response = new GetClientTrustedContactByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientTrustedContactNotFoundApplicationException.class,
                () -> new GetClientTrustedContactByIdUseCase(repository).execute(ClientTrustedContactId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListClientTrustedContactUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ClientTrustedContact aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ClientTrustedContactResponse response = new UpdateClientTrustedContactUseCase(repository).execute(
                new UpdateClientTrustedContactCommand(
                aggregate.id(),
                    ClientId.generate(),
                    TrustedContactId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ClientTrustedContact other = sample();
        assertThrows(ClientTrustedContactNotFoundApplicationException.class,
                () -> new UpdateClientTrustedContactUseCase(repository).execute(
                        new UpdateClientTrustedContactCommand(
                        other.id(),
                        ClientId.generate(),
                        TrustedContactId.generate())));
    }

    private static final class FakeRepository implements ClientTrustedContactRepository {
        private ClientTrustedContact aggregate;
        private ClientTrustedContact deletedAggregate;

        private FakeRepository(ClientTrustedContact aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ClientTrustedContact save(ClientTrustedContact value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ClientTrustedContact> findById(ClientTrustedContactId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ClientTrustedContact> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ClientTrustedContact value) {
            deletedAggregate = value;
        }

        private ClientTrustedContact deletedAggregate() {
            return deletedAggregate;
        }
    }
}
