package com.carelink.application.specialistrole.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialistrole.command.RegisterSpecialistRoleCommand;
import com.carelink.application.specialistrole.command.UpdateSpecialistRoleCommand;
import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.application.specialistrole.exception.SpecialistRoleNotFoundApplicationException;
import com.carelink.domain.specialistrole.event.SpecialistRoleDeletedEvent;
import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import org.junit.jupiter.api.Test;

class SpecialistRoleUseCasesTest {
    private static SpecialistRole sample() {
        return SpecialistRole.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SpecialistRoleResponse response = new RegisterSpecialistRoleUseCase(repository).execute(
                new RegisterSpecialistRoleCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SpecialistRole aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistRoleResponse response = new GetSpecialistRoleByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistRoleNotFoundApplicationException.class,
                () -> new GetSpecialistRoleByIdUseCase(repository).execute(SpecialistRoleId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSpecialistRoleUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SpecialistRole aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistRoleResponse response = new UpdateSpecialistRoleUseCase(repository).execute(
                new UpdateSpecialistRoleCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SpecialistRole other = sample();
        assertThrows(SpecialistRoleNotFoundApplicationException.class,
                () -> new UpdateSpecialistRoleUseCase(repository).execute(
                        new UpdateSpecialistRoleCommand(
                        other.id(),
                        "Sample")));
    }

    private static final class FakeRepository implements SpecialistRoleRepository {
        private SpecialistRole aggregate;
        private SpecialistRole deletedAggregate;

        private FakeRepository(SpecialistRole aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SpecialistRole save(SpecialistRole value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SpecialistRole> findById(SpecialistRoleId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SpecialistRole> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SpecialistRole value) {
            deletedAggregate = value;
        }

        private SpecialistRole deletedAggregate() {
            return deletedAggregate;
        }
    }
}
