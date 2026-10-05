package com.carelink.domain.common.exception;

import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;

public class SupportMessageNotFoundException extends RuntimeException {
    public SupportMessageNotFoundException(SupportMessageId id) {
        super("SupportMessage not found with id: " + id.value());
    }
}
