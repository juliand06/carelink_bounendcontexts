package com.carelink.application.supportconversation.command;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public record UpdateSupportConversationCommand(
        SupportConversationId id,
        SupportConversationStatusId conversationStatusId,
        UrgencyLevelId urgencyLevelId,
        LocalDateTime lastInteractionAt,
        Boolean isClosed,
        LocalDateTime closedAt,
        UUID closedBy
) {
    public UpdateSupportConversationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        Objects.requireNonNull(urgencyLevelId, "urgencyLevelId must not be null");
        Objects.requireNonNull(isClosed, "isClosed must not be null");
    }
}
