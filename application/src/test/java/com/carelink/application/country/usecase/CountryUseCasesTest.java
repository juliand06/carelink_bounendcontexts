package com.carelink.application.country.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.country.command.RegisterCountryCommand;
import com.carelink.application.country.command.UpdateCountryCommand;
import com.carelink.application.country.dto.CountryResponse;
import com.carelink.application.country.exception.CountryNotFoundApplicationException;
import com.carelink.domain.country.event.CountryDeletedEvent;
import com.carelink.domain.country.model.aggregate.Country;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.country.port.repository.CountryRepository;
import org.junit.jupiter.api.Test;

class CountryUseCasesTest {
    private static Country sample() {
        return Country.register(
                "Sample",
                "Sample",
                "Sample",
                true,
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        CountryResponse response = new RegisterCountryUseCase(repository).execute(
                new RegisterCountryCommand(
                "Sample",
                    "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        Country aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CountryResponse response = new GetCountryByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(CountryNotFoundApplicationException.class,
                () -> new GetCountryByIdUseCase(repository).execute(CountryId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListCountryUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        Country aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        CountryResponse response = new UpdateCountryUseCase(repository).execute(
                new UpdateCountryCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    "Sample",
                    true,
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        Country other = sample();
        assertThrows(CountryNotFoundApplicationException.class,
                () -> new UpdateCountryUseCase(repository).execute(
                        new UpdateCountryCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        "Sample",
                        true,
                        "Sample")));
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
