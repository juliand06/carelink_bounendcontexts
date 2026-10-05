package com.carelink.application.caregoalstatus.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class CareGoalStatusNotFoundApplicationException extends NotFoundApplicationException {
    public CareGoalStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
