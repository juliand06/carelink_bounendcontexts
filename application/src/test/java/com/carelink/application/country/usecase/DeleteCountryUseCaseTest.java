package com.carelink.application.country.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.country.exception.CountryNotFoundApplicationException;
import com.carelink.domain.country.event.CountryDeletedEvent;
import com.carelink.domain.country.model.aggregate.Country;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.country.port.repository.CountryRepository;
import org.junit.jupiter.api.Test;

class DeleteCountryUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        Country aggregate = Country.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        CountryDeletedEvent event = new DeleteCountryUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CountryNotFoundApplicationException.class,
                () -> new DeleteCountryUseCase(repository).execute(CountryId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements CountryRepository {
        private Country aggregate;
        private Country deletedAggregate;

        private FakeRepository(Country aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public Country save(Country value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<Country> findById(CountryId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<Country> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(Country value) {
            deletedAggregate = value;
        }

        private Country deletedAggregate() {
            return deletedAggregate;
        }
    }
}
