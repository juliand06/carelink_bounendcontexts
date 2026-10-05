package com.carelink.application.contactemailaddress.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactemailaddress.exception.ContactEmailAddressNotFoundApplicationException;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressDeletedEvent;
import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class DeleteContactEmailAddressUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ContactEmailAddress aggregate = ContactEmailAddress.register(
                TrustedContactId.generate(),
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        ContactEmailAddressDeletedEvent event = new DeleteContactEmailAddressUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactEmailAddressNotFoundApplicationException.class,
                () -> new DeleteContactEmailAddressUseCase(repository).execute(ContactEmailAddressId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ContactEmailAddressRepository {
        private ContactEmailAddress aggregate;
        private ContactEmailAddress deletedAggregate;

        private FakeRepository(ContactEmailAddress aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ContactEmailAddress save(ContactEmailAddress value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ContactEmailAddress> findById(ContactEmailAddressId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ContactEmailAddress> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ContactEmailAddress value) {
            deletedAggregate = value;
        }

        private ContactEmailAddress deletedAggregate() {
            return deletedAggregate;
        }
    }
}
