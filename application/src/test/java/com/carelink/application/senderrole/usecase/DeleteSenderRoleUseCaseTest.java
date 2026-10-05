package com.carelink.application.senderrole.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.senderrole.exception.SenderRoleNotFoundApplicationException;
import com.carelink.domain.senderrole.event.SenderRoleDeletedEvent;
import com.carelink.domain.senderrole.model.aggregate.SenderRole;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import org.junit.jupiter.api.Test;

class DeleteSenderRoleUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        SenderRole aggregate = SenderRole.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        SenderRoleDeletedEvent event = new DeleteSenderRoleUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SenderRoleNotFoundApplicationException.class,
                () -> new DeleteSenderRoleUseCase(repository).execute(SenderRoleId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements SenderRoleRepository {
        private SenderRole aggregate;
        private SenderRole deletedAggregate;

        private FakeRepository(SenderRole aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SenderRole save(SenderRole value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SenderRole> findById(SenderRoleId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SenderRole> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SenderRole value) {
            deletedAggregate = value;
        }

        private SenderRole deletedAggregate() {
            return deletedAggregate;
        }
    }
}
