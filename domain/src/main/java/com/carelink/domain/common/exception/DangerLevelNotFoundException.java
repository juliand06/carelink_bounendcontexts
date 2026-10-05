package com.carelink.domain.common.exception;

import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public class DangerLevelNotFoundException extends RuntimeException {
    public DangerLevelNotFoundException(DangerLevelId id) {
        super("DangerLevel not found with id: " + id.value());
    }
}
