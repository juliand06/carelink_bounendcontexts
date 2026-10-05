package com.carelink.application.contactphonenumber.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactphonenumber.exception.ContactPhoneNumberNotFoundApplicationException;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberDeletedEvent;
import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class DeleteContactPhoneNumberUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ContactPhoneNumber aggregate = ContactPhoneNumber.register(
                TrustedContactId.generate(),
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        ContactPhoneNumberDeletedEvent event = new DeleteContactPhoneNumberUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactPhoneNumberNotFoundApplicationException.class,
                () -> new DeleteContactPhoneNumberUseCase(repository).execute(ContactPhoneNumberId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ContactPhoneNumberRepository {
        private ContactPhoneNumber aggregate;
        private ContactPhoneNumber deletedAggregate;

        private FakeRepository(ContactPhoneNumber aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ContactPhoneNumber save(ContactPhoneNumber value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ContactPhoneNumber> findById(ContactPhoneNumberId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ContactPhoneNumber> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ContactPhoneNumber value) {
            deletedAggregate = value;
        }

        private ContactPhoneNumber deletedAggregate() {
            return deletedAggregate;
        }
    }
}
