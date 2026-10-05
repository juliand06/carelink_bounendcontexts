package com.carelink.application.supportconversation.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.carelink.application.supportconversation.command.RegisterSupportConversationCommand;
import com.carelink.application.supportconversation.command.UpdateSupportConversationCommand;
import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.application.supportconversation.exception.SupportConversationNotFoundApplicationException;
import com.carelink.domain.supportconversation.event.SupportConversationDeletedEvent;
import com.carelink.domain.supportconversation.model.aggregate.SupportConversation;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversation.port.repository.SupportConversationRepository;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import org.junit.jupiter.api.Test;

class SupportConversationUseCasesTest {
    private static SupportConversation sample() {
        return SupportConversation.register(
                SupportConversationStatusId.generate(),
                UrgencyLevelId.generate(),
                LocalDateTime.of(2026, 1, 10, 8, 0),
                true,
                LocalDateTime.of(2026, 1, 10, 8, 0),
                UUID.randomUUID());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SupportConversationResponse response = new RegisterSupportConversationUseCase(repository).execute(
                new RegisterSupportConversationCommand(
                SupportConversationStatusId.generate(),
                    UrgencyLevelId.generate(),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    true,
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    UUID.randomUUID()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SupportConversation aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationResponse response = new GetSupportConversationByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportConversationNotFoundApplicationException.class,
                () -> new GetSupportConversationByIdUseCase(repository).execute(SupportConversationId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSupportConversationUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SupportConversation aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationResponse response = new UpdateSupportConversationUseCase(repository).execute(
                new UpdateSupportConversationCommand(
                aggregate.id(),
                    SupportConversationStatusId.generate(),
                    UrgencyLevelId.generate(),
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    true,
                    LocalDateTime.of(2026, 1, 10, 8, 0),
                    UUID.randomUUID()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SupportConversation other = sample();
        assertThrows(SupportConversationNotFoundApplicationException.class,
                () -> new UpdateSupportConversationUseCase(repository).execute(
                        new UpdateSupportConversationCommand(
                        other.id(),
                        SupportConversationStatusId.generate(),
                        UrgencyLevelId.generate(),
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        true,
                        LocalDateTime.of(2026, 1, 10, 8, 0),
                        UUID.randomUUID())));
    }

    private static final class FakeRepository implements SupportConversationRepository {
        private SupportConversation aggregate;
        private SupportConversation deletedAggregate;

        private FakeRepository(SupportConversation aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SupportConversation save(SupportConversation value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SupportConversation> findById(SupportConversationId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SupportConversation> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SupportConversation value) {
            deletedAggregate = value;
        }

        private SupportConversation deletedAggregate() {
            return deletedAggregate;
        }
    }
}
