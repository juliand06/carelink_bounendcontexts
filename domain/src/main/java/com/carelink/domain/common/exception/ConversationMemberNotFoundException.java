package com.carelink.domain.common.exception;

import com.carelink.domain.conversationmember.model.valueobject.ConversationMemberId;

public class ConversationMemberNotFoundException extends RuntimeException {
    public ConversationMemberNotFoundException(ConversationMemberId id) {
        super("ConversationMember not found with id: " + id.value());
    }
}
