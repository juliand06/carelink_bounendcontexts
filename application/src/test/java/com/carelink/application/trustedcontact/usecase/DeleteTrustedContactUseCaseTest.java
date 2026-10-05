package com.carelink.application.trustedcontact.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.trustedcontact.exception.TrustedContactNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.event.TrustedContactDeletedEvent;
import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import org.junit.jupiter.api.Test;

class DeleteTrustedContactUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        TrustedContact aggregate = TrustedContact.register(
                "Sample",
                ContactRelationshipTypeId.generate(),
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        TrustedContactDeletedEvent event = new DeleteTrustedContactUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(TrustedContactNotFoundApplicationException.class,
                () -> new DeleteTrustedContactUseCase(repository).execute(TrustedContactId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements TrustedContactRepository {
        private TrustedContact aggregate;
        private TrustedContact deletedAggregate;

        private FakeRepository(TrustedContact aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public TrustedContact save(TrustedContact value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<TrustedContact> findById(TrustedContactId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<TrustedContact> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(TrustedContact value) {
            deletedAggregate = value;
        }

        private TrustedContact deletedAggregate() {
            return deletedAggregate;
        }
    }
}
