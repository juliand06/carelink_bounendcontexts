package com.carelink.application.academicprogram.usecase;

import java.util.List;

import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;

public class ListAcademicProgramUseCase {
    private final AcademicProgramRepository repository;

    public ListAcademicProgramUseCase(AcademicProgramRepository repository) {
        this.repository = repository;
    }

    public List<AcademicProgramResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AcademicProgramResponse(
                        aggregate.id().value(),
                        aggregate.title(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
