package com.carelink.domain.academicprogram.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AcademicProgramId(UUID value) {
    public AcademicProgramId {
        Objects.requireNonNull(value, "AcademicProgramId value must not be null");
    }

    public static AcademicProgramId generate() {
        return new AcademicProgramId(UUID.randomUUID());
    }
}
