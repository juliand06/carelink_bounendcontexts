package com.carelink.application.supportconversationstatus.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.supportconversationstatus.command.RegisterSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.command.UpdateSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.application.supportconversationstatus.exception.SupportConversationStatusNotFoundApplicationException;
import com.carelink.domain.supportconversationstatus.event.SupportConversationStatusDeletedEvent;
import com.carelink.domain.supportconversationstatus.model.aggregate.SupportConversationStatus;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.supportconversationstatus.port.repository.SupportConversationStatusRepository;
import org.junit.jupiter.api.Test;

class SupportConversationStatusUseCasesTest {
    private static SupportConversationStatus sample() {
        return SupportConversationStatus.register(
                "Sample",
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        SupportConversationStatusResponse response = new RegisterSupportConversationStatusUseCase(repository).execute(
                new RegisterSupportConversationStatusCommand(
                "Sample",
                    "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        SupportConversationStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationStatusResponse response = new GetSupportConversationStatusByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(SupportConversationStatusNotFoundApplicationException.class,
                () -> new GetSupportConversationStatusByIdUseCase(repository).execute(SupportConversationStatusId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListSupportConversationStatusUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        SupportConversationStatus aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        SupportConversationStatusResponse response = new UpdateSupportConversationStatusUseCase(repository).execute(
                new UpdateSupportConversationStatusCommand(
                aggregate.id(),
                    "Sample",
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        SupportConversationStatus other = sample();
        assertThrows(SupportConversationStatusNotFoundApplicationException.class,
                () -> new UpdateSupportConversationStatusUseCase(repository).execute(
                        new UpdateSupportConversationStatusCommand(
                        other.id(),
                        "Sample",
                        "Sample")));
    }

    private static final class FakeRepository implements SupportConversationStatusRepository {
        private SupportConversationStatus aggregate;
        private SupportConversationStatus deletedAggregate;

        private FakeRepository(SupportConversationStatus aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public SupportConversationStatus save(SupportConversationStatus value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<SupportConversationStatus> findById(SupportConversationStatusId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<SupportConversationStatus> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(SupportConversationStatus value) {
            deletedAggregate = value;
        }

        private SupportConversationStatus deletedAggregate() {
            return deletedAggregate;
        }
    }
}
