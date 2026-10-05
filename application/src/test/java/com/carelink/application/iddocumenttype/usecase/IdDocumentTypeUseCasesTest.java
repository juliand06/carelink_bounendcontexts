package com.carelink.application.iddocumenttype.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.iddocumenttype.command.RegisterIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.command.UpdateIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.application.iddocumenttype.exception.IdDocumentTypeNotFoundApplicationException;
import com.carelink.domain.iddocumenttype.event.IdDocumentTypeDeletedEvent;
import com.carelink.domain.iddocumenttype.model.aggregate.IdDocumentType;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.iddocumenttype.port.repository.IdDocumentTypeRepository;
import org.junit.jupiter.api.Test;

class IdDocumentTypeUseCasesTest {
    private static IdDocumentType sample() {
        return IdDocumentType.register(
                "Sample",
                "Sample",
                true);
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        IdDocumentTypeResponse response = new RegisterIdDocumentTypeUseCase(repository).execute(
                new RegisterIdDocumentTypeCommand(
                "Sample",
                    "Sample",
                    true));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        IdDocumentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        IdDocumentTypeResponse response = new GetIdDocumentTypeByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(IdDocumentTypeNotFoundApplicationException.class,
                () -> new GetIdDocumentTypeByIdUseCase(repository).execute(IdDocumentTypeId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListIdDocumentTypeUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        IdDocumentType aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        IdDocumentTypeResponse response = new UpdateIdDocumentTypeUseCase(repository).execute(
                new UpdateIdDocumentTypeCommand(
                aggregate.id(),
                    "Sample",
                    "Sample",
                    true));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        IdDocumentType other = sample();
        assertThrows(IdDocumentTypeNotFoundApplicationException.class,
                () -> new UpdateIdDocumentTypeUseCase(repository).execute(
                        new UpdateIdDocumentTypeCommand(
                        other.id(),
                        "Sample",
                        "Sample",
                        true)));
    }

    private static final class FakeRepository implements IdDocumentTypeRepository {
        private IdDocumentType aggregate;
        private IdDocumentType deletedAggregate;

        private FakeRepository(IdDocumentType aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public IdDocumentType save(IdDocumentType value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<IdDocumentType> findById(IdDocumentTypeId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<IdDocumentType> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(IdDocumentType value) {
            deletedAggregate = value;
        }

        private IdDocumentType deletedAggregate() {
            return deletedAggregate;
        }
    }
}
