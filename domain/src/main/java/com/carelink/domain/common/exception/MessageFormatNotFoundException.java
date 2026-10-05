package com.carelink.domain.common.exception;

import com.carelink.domain.messageformat.model.valueobject.MessageFormatId;

public class MessageFormatNotFoundException extends RuntimeException {
    public MessageFormatNotFoundException(MessageFormatId id) {
        super("MessageFormat not found with id: " + id.value());
    }
}
