package com.carelink.application.senderrole.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.senderrole.command.RegisterSenderRoleCommand;
import com.carelink.application.senderrole.command.UpdateSenderRoleCommand;
import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.application.senderrole.exception.SenderRoleNotFoundApplicationException;
import com.carelink.domain.senderrole.event.SenderRoleDeletedEvent;
import com.carelink.domain.senderrole.model.aggregate.SenderRole;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import org.junit.jupiter.api.Test;

class SenderRoleUseCasesTest {
    private static SenderRole sample() {
        return SenderRole.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SenderRoleResponse response = new RegisterSenderRoleUseCase(repository).execute(
                new RegisterSenderRoleCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SenderRole aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SenderRoleResponse response = new GetSenderRoleByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SenderRoleNotFoundApplicationException.class,
                () -> new GetSenderRoleByIdUseCase(repository).execute(SenderRoleId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSenderRoleUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SenderRole aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SenderRoleResponse response = new UpdateSenderRoleUseCase(repository).execute(
                new UpdateSenderRoleCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SenderRole other = sample();
        assertThrows(SenderRoleNotFoundApplicationException.class,
                () -> new UpdateSenderRoleUseCase(repository).execute(
                        new UpdateSenderRoleCommand(
                        other.id(),
                        "Sample")));
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
