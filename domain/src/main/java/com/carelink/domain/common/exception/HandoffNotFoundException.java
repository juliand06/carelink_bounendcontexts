package com.carelink.domain.common.exception;

import com.carelink.domain.handoff.model.valueobject.HandoffId;

public class HandoffNotFoundException extends RuntimeException {
    public HandoffNotFoundException(HandoffId id) {
        super("Handoff not found with id: " + id.value());
    }
}
