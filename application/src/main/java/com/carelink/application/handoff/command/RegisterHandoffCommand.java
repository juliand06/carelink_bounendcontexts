package com.carelink.application.handoff.command;

import java.util.Objects;

import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;

public record RegisterHandoffCommand(
        SupportConversationId conversationId,
        HandoffStatusId handoffStatusId,
        Boolean originatedFromAi,
        String handoffReason
) {
    public RegisterHandoffCommand {
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(handoffStatusId, "handoffStatusId must not be null");
        Objects.requireNonNull(originatedFromAi, "originatedFromAi must not be null");
    }
}
