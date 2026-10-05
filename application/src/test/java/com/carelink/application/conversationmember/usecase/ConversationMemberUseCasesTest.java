package com.carelink.application.conversationmember.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.carelink.application.conversationmember.command.RegisterConversationMemberCommand;
import com.carelink.application.conversationmember.command.UpdateConversationMemberCommand;
import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
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

class ConversationMemberUseCasesTest {
    private static ConversationMember sample() {
        return ConversationMember.register(
                SupportConversationId.generate(),
                ClientId.generate(),
                null,
                SenderRoleId.generate());
    }

    @Test
    void shouldRegisterAndReturnResponse() {
        FakeRepository repository = new FakeRepository(null);
        ConversationMemberResponse response = new RegisterConversationMemberUseCase(repository).execute(
                new RegisterConversationMemberCommand(
                SupportConversationId.generate(),
                    ClientId.generate(),
                    null,
                    SenderRoleId.generate()));
        assertNotNull(response.id());
        assertNotNull(repository.findAll().get(0));
    }

    @Test
    void shouldGetExistingAggregateById() {
        ConversationMember aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationMemberResponse response = new GetConversationMemberByIdUseCase(repository).execute(aggregate.id());
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailGettingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        assertThrows(ConversationMemberNotFoundApplicationException.class,
                () -> new GetConversationMemberByIdUseCase(repository).execute(ConversationMemberId.generate()));
    }

    @Test
    void shouldListAggregates() {
        FakeRepository repository = new FakeRepository(sample());
        assertEquals(1, new ListConversationMemberUseCase(repository).execute().size());
    }

    @Test
    void shouldUpdateExistingAggregate() {
        ConversationMember aggregate = sample();
        FakeRepository repository = new FakeRepository(aggregate);
        ConversationMemberResponse response = new UpdateConversationMemberUseCase(repository).execute(
                new UpdateConversationMemberCommand(
                aggregate.id(),
                    SupportConversationId.generate(),
                    ClientId.generate(),
                    null,
                    SenderRoleId.generate()));
        assertEquals(aggregate.id().value(), response.id());
    }

    @Test
    void shouldFailUpdatingMissingAggregate() {
        FakeRepository repository = new FakeRepository(null);
        ConversationMember other = sample();
        assertThrows(ConversationMemberNotFoundApplicationException.class,
                () -> new UpdateConversationMemberUseCase(repository).execute(
                        new UpdateConversationMemberCommand(
                        other.id(),
                        SupportConversationId.generate(),
                        ClientId.generate(),
                        null,
                        SenderRoleId.generate())));
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
