package com.carelink.application.caregoal.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class CareGoalNotFoundApplicationException extends NotFoundApplicationException {
    public CareGoalNotFoundApplicationException(String message) {
        super(message);
    }
}
