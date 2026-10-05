package com.carelink.application.contactrelationshiptype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.contactrelationshiptype.exception.ContactRelationshipTypeNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeDeletedEvent;
import com.carelink.domain.contactrelationshiptype.model.aggregate.ContactRelationshipType;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.contactrelationshiptype.port.repository.ContactRelationshipTypeRepository;
import org.junit.jupiter.api.Test;

class DeleteContactRelationshipTypeUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ContactRelationshipType aggregate = ContactRelationshipType.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        ContactRelationshipTypeDeletedEvent event = new DeleteContactRelationshipTypeUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ContactRelationshipTypeNotFoundApplicationException.class,
                () -> new DeleteContactRelationshipTypeUseCase(repository).execute(ContactRelationshipTypeId.generate()));
        assertNull(repository.deletedAggregate());
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
