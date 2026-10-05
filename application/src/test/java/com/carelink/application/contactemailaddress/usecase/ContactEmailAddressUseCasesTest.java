package com.carelink.application.contactemailaddress.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactemailaddress.command.RegisterContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.command.UpdateContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.application.contactemailaddress.exception.ContactEmailAddressNotFoundApplicationException;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressDeletedEvent;
import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import org.junit.jupiter.api.Test;

class ContactEmailAddressUseCasesTest {
    private static ContactEmailAddress sample() {
        return ContactEmailAddress.register(
                TrustedContactId.generate(),
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ContactEmailAddressResponse response = new RegisterContactEmailAddressUseCase(repository).execute(
                new RegisterContactEmailAddressCommand(
                TrustedContactId.generate(),
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ContactEmailAddress aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactEmailAddressResponse response = new GetContactEmailAddressByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactEmailAddressNotFoundApplicationException.class,
                () -> new GetContactEmailAddressByIdUseCase(repository).execute(ContactEmailAddressId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListContactEmailAddressUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ContactEmailAddress aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactEmailAddressResponse response = new UpdateContactEmailAddressUseCase(repository).execute(
                new UpdateContactEmailAddressCommand(
                aggregate.id(),
                    TrustedContactId.generate(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ContactEmailAddress other = sample();
        assertThrows(ContactEmailAddressNotFoundApplicationException.class,
                () -> new UpdateContactEmailAddressUseCase(repository).execute(
                        new UpdateContactEmailAddressCommand(
                        other.id(),
                        TrustedContactId.generate(),
                        "Sample")));
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
