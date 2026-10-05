package com.carelink.application.department.usecase;

import java.time.LocalDateTime;

import com.carelink.application.department.exception.DepartmentNotFoundApplicationException;
import com.carelink.domain.department.event.DepartmentDeletedEvent;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.department.port.repository.DepartmentRepository;

public class DeleteDepartmentUseCase {
    private final DepartmentRepository repository;

    public DeleteDepartmentUseCase(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentDeletedEvent execute(DepartmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DepartmentDeletedEvent(id, LocalDateTime.now());
    }
}
