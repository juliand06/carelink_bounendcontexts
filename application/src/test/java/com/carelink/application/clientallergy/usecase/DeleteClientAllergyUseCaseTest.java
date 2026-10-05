package com.carelink.application.clientallergy.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.clientallergy.exception.ClientAllergyNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.event.ClientAllergyDeletedEvent;
import com.carelink.domain.clientallergy.model.aggregate.ClientAllergy;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.clientallergy.port.repository.ClientAllergyRepository;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import org.junit.jupiter.api.Test;

class DeleteClientAllergyUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ClientAllergy aggregate = ClientAllergy.register(
                ClientId.generate(),
                "Sample",
                "Sample",
                "Sample",
                true,
                SpecialistId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ClientAllergyDeletedEvent event = new DeleteClientAllergyUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ClientAllergyNotFoundApplicationException.class,
                () -> new DeleteClientAllergyUseCase(repository).execute(ClientAllergyId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ClientAllergyRepository {
        private ClientAllergy aggregate;
        private ClientAllergy deletedAggregate;

        private FakeRepository(ClientAllergy aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ClientAllergy save(ClientAllergy value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ClientAllergy> findById(ClientAllergyId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ClientAllergy> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ClientAllergy value) {
            deletedAggregate = value;
        }

        private ClientAllergy deletedAggregate() {
            return deletedAggregate;
        }
    }
}
