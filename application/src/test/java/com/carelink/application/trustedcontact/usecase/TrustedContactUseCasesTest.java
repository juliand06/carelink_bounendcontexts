package com.carelink.application.trustedcontact.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.trustedcontact.command.RegisterTrustedContactCommand;
import com.carelink.application.trustedcontact.command.UpdateTrustedContactCommand;
import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.application.trustedcontact.exception.TrustedContactNotFoundApplicationException;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.event.TrustedContactDeletedEvent;
import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import org.junit.jupiter.api.Test;

class TrustedContactUseCasesTest {
    private static TrustedContact sample() {
        return TrustedContact.register(
                "Sample",
                ContactRelationshipTypeId.generate(),
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        TrustedContactResponse response = new RegisterTrustedContactUseCase(repository).execute(
                new RegisterTrustedContactCommand(
                "Sample",
                    ContactRelationshipTypeId.generate(),
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        TrustedContact aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        TrustedContactResponse response = new GetTrustedContactByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(TrustedContactNotFoundApplicationException.class,
                () -> new GetTrustedContactByIdUseCase(repository).execute(TrustedContactId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListTrustedContactUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        TrustedContact aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        TrustedContactResponse response = new UpdateTrustedContactUseCase(repository).execute(
                new UpdateTrustedContactCommand(
                aggregate.id(),
                    "Sample",
                    ContactRelationshipTypeId.generate(),
                    true,
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        TrustedContact other = sample();
        assertThrows(TrustedContactNotFoundApplicationException.class,
                () -> new UpdateTrustedContactUseCase(repository).execute(
                        new UpdateTrustedContactCommand(
                        other.id(),
                        "Sample",
                        ContactRelationshipTypeId.generate(),
                        true,
                        "Sample")));
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
