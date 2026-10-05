package com.carelink.application.department.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class DepartmentNotFoundApplicationException extends NotFoundApplicationException {
    public DepartmentNotFoundApplicationException(String message) {
        super(message);
    }
}
