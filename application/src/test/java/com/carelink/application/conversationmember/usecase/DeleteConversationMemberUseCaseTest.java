package com.carelink.application.conversationmember.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.conversationmember.exception.ConversationMemberNotFoundApplicationException;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.conversationmember.event.ConversationMemberDeletedEvent;
import com.carelink.domain.conversationmember.model.aggregate.ConversationMember;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import org.junit.jupiter.api.Test;

class DeleteConversationMemberUseCaseTest {
    @Test
    void shouldDeleteExistingAggregate() {
        ConversationMember aggregate = ConversationMember.register(
                SupportConversationId.generate(),
                ClientId.generate(),
                null,
                SenderRoleId.generate());
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationMemberDeletedEvent event = new DeleteConversationMemberUseCase(repository).execute(aggregate.id());
        assertSame(aggregate, repository.deletedAggregate());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }

    @Test
    void shouldRejectDeletionWhenAggregateDoesNotExist() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ConversationMemberNotFoundApplicationException.class,
                () -> new DeleteConversationMemberUseCase(repository).execute(ConversationMemberId.generate()));
        assertNull(repository.deletedAggregate());
    }

    private static final class FakeRepository implements ConversationMemberRepository {
        private ConversationMember aggregate;
        private ConversationMember deletedAggregate;

        private FakeRepository(ConversationMember aggregate) {
            this.aggregate = aggregate;
        }

        @Override
        public ConversationMember save(ConversationMember value) {
            this.aggregate = value;
            return value;
        }

        @Override
        public Optional<ConversationMember> findById(ConversationMemberId id) {
            return Optional.ofNullable(aggregate).filter(v -> v.id().equals(id));
        }

        @Override
        public List<ConversationMember> findAll() {
            return aggregate == null ? List.of() : List.of(aggregate);
        }

        @Override
        public void delete(ConversationMember value) {
            deletedAggregate = value;
        }

        private ConversationMember deletedAggregate() {
            return deletedAggregate;
        }
    }
}
