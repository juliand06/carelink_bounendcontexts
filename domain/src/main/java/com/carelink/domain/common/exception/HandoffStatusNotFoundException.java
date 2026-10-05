package com.carelink.domain.common.exception;

import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public class HandoffStatusNotFoundException extends RuntimeException {
    public HandoffStatusNotFoundException(HandoffStatusId id) {
        super("HandoffStatus not found with id: " + id.value());
    }
}
