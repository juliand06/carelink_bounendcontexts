package com.carelink.application.handoffstatusaudit.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class HandoffStatusAuditNotFoundApplicationException extends NotFoundApplicationException {
    public HandoffStatusAuditNotFoundApplicationException(String message) {
        super(message);
    }
}
