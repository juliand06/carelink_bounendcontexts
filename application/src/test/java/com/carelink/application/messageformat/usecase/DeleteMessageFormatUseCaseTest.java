package com.carelink.application.messageformat.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.messageformat.exception.MessageFormatNotFoundApplicationException;
import com.carelink.domain.messageformat.event.MessageFormatDeletedEvent;
import com.carelink.domain.messageformat.model.aggregate.MessageFormat;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.messageformat.port.repository.MessageFormatRepository;
import org.junit.jupiter.api.Test;

class DeleteMessageFormatUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        MessageFormat aggregate = MessageFormat.register(
                "Sample");
        FakeRepository repository = new FakeRepository(aggregate);
        MessageFormatDeletedEvent event = new DeleteMessageFormatUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(MessageFormatNotFoundApplicationException.class,
                () -> new DeleteMessageFormatUseCase(repository).execute(MessageFormatId.generate()));
        assertNull(repository.deletedAggregate());
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
