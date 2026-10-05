package com.carelink.application.messageformat.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.messageformat.command.RegisterMessageFormatCommand;
import com.carelink.application.messageformat.command.UpdateMessageFormatCommand;
import com.carelink.application.messageformat.dto.MessageFormatResponse;
import com.carelink.application.messageformat.exception.MessageFormatNotFoundApplicationException;
import com.carelink.domain.messageformat.event.MessageFormatDeletedEvent;
import com.carelink.domain.messageformat.model.aggregate.MessageFormat;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import org.junit.jupiter.api.Test;

class MessageFormatUseCasesTest {
    private static MessageFormat sample() {
        return MessageFormat.register(
                "Sample");
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        MessageFormatResponse response = new RegisterMessageFormatUseCase(repository).execute(
                new RegisterMessageFormatCommand(
                "Sample"));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        MessageFormat aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MessageFormatResponse response = new GetMessageFormatByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MessageFormatNotFoundApplicationException.class,
                () -> new GetMessageFormatByIdUseCase(repository).execute(MessageFormatId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListMessageFormatUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        MessageFormat aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        MessageFormatResponse response = new UpdateMessageFormatUseCase(repository).execute(
                new UpdateMessageFormatCommand(
                aggregate.id(),
                    "Sample"));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        MessageFormat other = sample();
        assertThrows(MessageFormatNotFoundApplicationException.class,
                () -> new UpdateMessageFormatUseCase(repository).execute(
                        new UpdateMessageFormatCommand(
                        other.id(),
                        "Sample")));
    }

    private static final class FakeRepository implements MessageFormatRepository {
        private MessageFormat aggregate;
        private MessageFormat deletedAggregate;

        private FakeRepository(MessageFormat aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public MessageFormat save(MessageFormat value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<MessageFormat> findById(MessageFormatId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<MessageFormat> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(MessageFormat value) {
            deletedAggregate = value;
        }

        private MessageFormat deletedAggregate() {
            return deletedAggregate;
        }
    }
}
