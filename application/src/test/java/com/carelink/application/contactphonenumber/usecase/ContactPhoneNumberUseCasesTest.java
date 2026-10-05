package com.carelink.application.contactphonenumber.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactphonenumber.command.RegisterContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.command.UpdateContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.application.contactphonenumber.exception.ContactPhoneNumberNotFoundApplicationException;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberDeletedEvent;
import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ContactPhoneNumberUseCasesTest {
    private static ContactPhoneNumber sample() {
        return ContactPhoneNumber.register(
                TrustedContactId.generate(),
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ContactPhoneNumberResponse response = new RegisterContactPhoneNumberUseCase(repository).execute(
                new RegisterContactPhoneNumberCommand(
                TrustedContactId.generate(),
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ContactPhoneNumber aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactPhoneNumberResponse response = new GetContactPhoneNumberByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactPhoneNumberNotFoundApplicationException.class,
                () -> new GetContactPhoneNumberByIdUseCase(repository).execute(ContactPhoneNumberId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListContactPhoneNumberUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ContactPhoneNumber aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactPhoneNumberResponse response = new UpdateContactPhoneNumberUseCase(repository).execute(
                new UpdateContactPhoneNumberCommand(
                aggregate.id(),
                    TrustedContactId.generate(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ContactPhoneNumber other = sample();
        assertThrows(ContactPhoneNumberNotFoundApplicationException.class,
                () -> new UpdateContactPhoneNumberUseCase(repository).execute(
                        new UpdateContactPhoneNumberCommand(
                        other.id(),
                        TrustedContactId.generate(),
                        "Sample")));
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
