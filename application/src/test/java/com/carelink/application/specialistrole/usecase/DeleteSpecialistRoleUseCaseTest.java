package com.carelink.application.specialistrole.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.specialistrole.exception.SpecialistRoleNotFoundApplicationException;
import com.carelink.domain.specialistrole.event.SpecialistRoleDeletedEvent;
import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import org.junit.jupiter.api.Test;

class DeleteSpecialistRoleUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SpecialistRole aggregate = SpecialistRole.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        SpecialistRoleDeletedEvent event = new DeleteSpecialistRoleUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SpecialistRoleNotFoundApplicationException.class,
                () -> new DeleteSpecialistRoleUseCase(repository).execute(SpecialistRoleId.generate()));
        assertNull(repository.deletedAggregate());
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
