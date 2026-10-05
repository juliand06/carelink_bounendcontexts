package com.carelink.application.supportmessage.command;

import java.util.Objects;

import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;
import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record RegisterSupportMessageCommand(
        SupportConversationId conversationId,
        MessageFormatId messageFormatId,
        ConversationMemberId memberId,
        String payload,
        String extraData
) {
    public RegisterSupportMessageCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(messageFormatId, "messageFormatId must not be null");
        Objects.requireNonNull(memberId, "memberId must not be null");
        Objects.requireNonNull(payload, "payload must not be null");
    }
}
