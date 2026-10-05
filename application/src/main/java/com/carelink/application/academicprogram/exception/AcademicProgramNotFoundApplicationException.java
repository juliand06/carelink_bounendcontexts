package com.carelink.application.academicprogram.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class AcademicProgramNotFoundApplicationException extends NotFoundApplicationException {
    public AcademicProgramNotFoundApplicationException(String message) {
        super(message);
    }
}
