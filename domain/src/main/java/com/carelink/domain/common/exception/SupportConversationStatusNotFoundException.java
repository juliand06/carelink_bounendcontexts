package com.carelink.domain.common.exception;

import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;

public class SupportConversationStatusNotFoundException extends RuntimeException {
    public SupportConversationStatusNotFoundException(SupportConversationStatusId id) {
        super("SupportConversationStatus not found with id: " + id.value());
    }
}
