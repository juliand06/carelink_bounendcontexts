package com.carelink.application.academicprogram.usecase;

import com.carelink.application.academicprogram.command.RegisterAcademicProgramCommand;
import com.carelink.application.academicprogram.dto.AcademicProgramResponse;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;
import com.carelink.domain.academicprogram.model.aggregate.AcademicProgram;

public class RegisterAcademicProgramUseCase {
    private final AcademicProgramRepository repository;

    public RegisterAcademicProgramUseCase(AcademicProgramRepository repository) {
        this.repository = repository;
    }

    public AcademicProgramResponse execute(RegisterAcademicProgramCommand command) {
        AcademicProgram aggregate = AcademicProgram.register(
                command.title());
        AcademicProgram saved = repository.save(aggregate);
        return new AcademicProgramResponse(
                saved.id().value(),
                saved.title(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
