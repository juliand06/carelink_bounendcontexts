package com.carelink.application.biologicalsex.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.biologicalsex.command.RegisterBiologicalSexCommand;
import com.carelink.application.biologicalsex.command.UpdateBiologicalSexCommand;
import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.application.biologicalsex.exception.BiologicalSexNotFoundApplicationException;
import com.carelink.domain.biologicalsex.event.BiologicalSexDeletedEvent;
import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import org.junit.jupiter.api.Test;

class BiologicalSexUseCasesTest {
    private static BiologicalSex sample() {
        return BiologicalSex.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        BiologicalSexResponse response = new RegisterBiologicalSexUseCase(repository).execute(
                new RegisterBiologicalSexCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        BiologicalSex aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        BiologicalSexResponse response = new GetBiologicalSexByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(BiologicalSexNotFoundApplicationException.class,
                () -> new GetBiologicalSexByIdUseCase(repository).execute(BiologicalSexId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListBiologicalSexUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        BiologicalSex aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        BiologicalSexResponse response = new UpdateBiologicalSexUseCase(repository).execute(
                new UpdateBiologicalSexCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        BiologicalSex other = sample();
        assertThrows(BiologicalSexNotFoundApplicationException.class,
                () -> new UpdateBiologicalSexUseCase(repository).execute(
                        new UpdateBiologicalSexCommand(
                        other.id(),
                        "Sample")));
    }

    private static final class FakeRepository implements BiologicalSexRepository {
        private BiologicalSex aggregate;
        private BiologicalSex deletedAggregate;

        private FakeRepository(BiologicalSex aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public BiologicalSex save(BiologicalSex value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<BiologicalSex> findById(BiologicalSexId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<BiologicalSex> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(BiologicalSex value) {
            deletedAggregate = value;
        }

        private BiologicalSex deletedAggregate() {
            return deletedAggregate;
        }
    }
}
