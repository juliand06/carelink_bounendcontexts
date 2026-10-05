package com.carelink.domain.academicprogram.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;

public interface AcademicProgramRepository {
    AcademicProgram save(AcademicProgram aggregate);

    Optional<AcademicProgram> findById(AcademicProgramId id);

    List<AcademicProgram> findAll();

    void delete(AcademicProgram aggregate);
}
