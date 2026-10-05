package com.carelink.application.academicprogram.usecase;

import java.time.LocalDateTime;

import com.carelink.application.academicprogram.exception.AcademicProgramNotFoundApplicationException;
import com.carelink.domain.academicprogram.event.AcademicProgramDeletedEvent;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.academicprogram.port.repository.AcademicProgramRepository;

public class DeleteAcademicProgramUseCase {
    private final AcademicProgramRepository repository;

    public DeleteAcademicProgramUseCase(AcademicProgramRepository repository) {
        this.repository = repository;
    }

    public AcademicProgramDeletedEvent execute(AcademicProgramId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AcademicProgramNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AcademicProgramDeletedEvent(id, LocalDateTime.now());
    }
}
