package com.carelink.application.academicprogram.usecase;

import com.carelink.application.academicprogram.command.UpdateAcademicProgramCommand;
import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import com.carelink.application.academicprogram.exception.AcademicProgramNotFoundApplicationException;

public class UpdateAcademicProgramUseCase {
    private final AcademicProgramRepository repository;

    public UpdateAcademicProgramUseCase(AcademicProgramRepository repository) {
        this.repository = repository;
    }

    public AcademicProgramResponse execute(UpdateAcademicProgramCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AcademicProgramNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.title());
        var saved = repository.save(aggregate);
        return new AcademicProgramResponse(
                saved.id().value(),
                saved.title(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
