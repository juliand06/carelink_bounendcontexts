package com.carelink.application.supportconversation.command;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public record RegisterSupportConversationCommand(
        SupportConversationStatusId conversationStatusId,
        UrgencyLevelId urgencyLevelId,
        LocalDateTime lastInteractionAt,
        Boolean isClosed,
        LocalDateTime closedAt,
        UUID closedBy
) {
    public RegisterSupportConversationCommand {
        Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        Objects.requireNonNull(urgencyLevelId, "urgencyLevelId must not be null");
        Objects.requireNonNull(isClosed, "isClosed must not be null");
    }
}
