package com.carelink.application.conversationmember.command;

import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record RegisterConversationMemberCommand(
        SupportConversationId conversationId,
        ClientId clientId,
        SpecialistId specialistId,
        SenderRoleId senderRoleId
) {
    public RegisterConversationMemberCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(senderRoleId, "senderRoleId must not be null");
    }
}
