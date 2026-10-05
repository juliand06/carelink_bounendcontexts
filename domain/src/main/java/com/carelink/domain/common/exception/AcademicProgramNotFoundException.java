package com.carelink.domain.common.exception;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;

public class AcademicProgramNotFoundException extends RuntimeException {
    public AcademicProgramNotFoundException(AcademicProgramId id) {
        super("AcademicProgram not found with id: " + id.value());
    }
}
