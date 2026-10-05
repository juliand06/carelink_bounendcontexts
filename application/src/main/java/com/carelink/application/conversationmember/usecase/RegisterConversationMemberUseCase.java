package com.carelink.application.conversationmember.usecase;

import com.carelink.application.conversationmember.command.RegisterConversationMemberCommand;
import com.carelink.application.conversationmember.dto.ConversationMemberResponse;
import com.carelink.domain.conversationmember.port.repository.ConversationMemberRepository;
import com.carelink.domain.conversationmember.model.aggregate.ConversationMember;

public class RegisterConversationMemberUseCase {
    private final ConversationMemberRepository repository;

    public RegisterConversationMemberUseCase(ConversationMemberRepository repository) {
        this.repository = repository;
    }

    public ConversationMemberResponse execute(RegisterConversationMemberCommand command) {
        ConversationMember aggregate = ConversationMember.register(
                command.conversationId(),
                command.clientId(),
                command.specialistId(),
                command.senderRoleId());
        ConversationMember saved = repository.save(aggregate);
        return new ConversationMemberResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.clientId() == null ? null : saved.clientId().value(),
                saved.specialistId() == null ? null : saved.specialistId().value(),
                saved.senderRoleId().value(),
                saved.joinedAt());
    }
}
