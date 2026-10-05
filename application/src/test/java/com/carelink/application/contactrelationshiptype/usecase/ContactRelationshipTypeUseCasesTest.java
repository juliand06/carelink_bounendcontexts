package com.carelink.application.contactrelationshiptype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactrelationshiptype.command.RegisterContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.command.UpdateContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.application.contactrelationshiptype.exception.ContactRelationshipTypeNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeDeletedEvent;
import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import org.junit.jupiter.api.Test;

class ContactRelationshipTypeUseCasesTest {
    private static ContactRelationshipType sample() {
        return ContactRelationshipType.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ContactRelationshipTypeResponse response = new RegisterContactRelationshipTypeUseCase(repository).execute(
                new RegisterContactRelationshipTypeCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ContactRelationshipType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactRelationshipTypeResponse response = new GetContactRelationshipTypeByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactRelationshipTypeNotFoundApplicationException.class,
                () -> new GetContactRelationshipTypeByIdUseCase(repository).execute(ContactRelationshipTypeId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListContactRelationshipTypeUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ContactRelationshipType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ContactRelationshipTypeResponse response = new UpdateContactRelationshipTypeUseCase(repository).execute(
                new UpdateContactRelationshipTypeCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ContactRelationshipType other = sample();
        assertThrows(ContactRelationshipTypeNotFoundApplicationException.class,
                () -> new UpdateContactRelationshipTypeUseCase(repository).execute(
                        new UpdateContactRelationshipTypeCommand(
                        other.id(),
                        "Sample")));
    }

    private static final class FakeRepository implements ContactRelationshipTypeRepository {
        private ContactRelationshipType aggregate;
        private ContactRelationshipType deletedAggregate;

        private FakeRepository(ContactRelationshipType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ContactRelationshipType save(ContactRelationshipType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ContactRelationshipType> findById(ContactRelationshipTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ContactRelationshipType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ContactRelationshipType value) {
            deletedAggregate = value;
        }

        private ContactRelationshipType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
