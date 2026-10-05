package com.carelink.application.conversationmember.command;

import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record UpdateConversationMemberCommand(
        ConversationMemberId id,
        SupportConversationId conversationId,
        ClientId clientId,
        SpecialistId specialistId,
        SenderRoleId senderRoleId
) {
    public UpdateConversationMemberCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(senderRoleId, "senderRoleId must not be null");
    }
}
