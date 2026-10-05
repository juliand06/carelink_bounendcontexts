package com.carelink.domain.common.exception;

import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public class HandoffStatusAuditNotFoundException extends RuntimeException {
    public HandoffStatusAuditNotFoundException(HandoffStatusAuditId id) {
        super("HandoffStatusAudit not found with id: " + id.value());
    }
}
