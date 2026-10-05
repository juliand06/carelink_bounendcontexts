package com.carelink.domain.common.exception;

import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public class UrgencyLevelNotFoundException extends RuntimeException {
    public UrgencyLevelNotFoundException(UrgencyLevelId id) {
        super("UrgencyLevel not found with id: " + id.value());
    }
}
