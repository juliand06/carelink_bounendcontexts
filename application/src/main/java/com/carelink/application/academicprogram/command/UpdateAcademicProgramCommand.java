package com.carelink.application.academicprogram.command;

import java.util.Objects;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;

public record UpdateAcademicProgramCommand(
        AcademicProgramId id,
        String title
) {
    public UpdateAcademicProgramCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(title, "title must not be null");
    }
}
