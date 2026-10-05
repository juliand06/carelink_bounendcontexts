package com.carelink.application.clienttrustedcontact.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.clienttrustedcontact.exception.ClientTrustedContactNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.event.ClientTrustedContactDeletedEvent;
import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class DeleteClientTrustedContactUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ClientTrustedContact aggregate = ClientTrustedContact.register(
                ClientId.generate(),
                TrustedContactId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ClientTrustedContactDeletedEvent event = new DeleteClientTrustedContactUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientTrustedContactNotFoundApplicationException.class,
                () -> new DeleteClientTrustedContactUseCase(repository).execute(ClientTrustedContactId.generate()));
        assertNull(repository.deletedAggregate());
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
