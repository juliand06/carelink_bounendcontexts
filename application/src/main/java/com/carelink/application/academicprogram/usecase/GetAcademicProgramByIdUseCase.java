package com.carelink.application.academicprogram.usecase;

import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import com.carelink.application.academicprogram.exception.AcademicProgramNotFoundApplicationException;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;

public class GetAcademicProgramByIdUseCase {
    private final AcademicProgramRepository repository;

    public GetAcademicProgramByIdUseCase(AcademicProgramRepository repository) {
        this.repository = repository;
    }

    public AcademicProgramResponse execute(AcademicProgramId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AcademicProgramNotFoundApplicationException(id.value().toString()));
        return new AcademicProgramResponse(
                aggregate.id().value(),
                aggregate.title(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
