package com.carelink.application.department.usecase;

import com.carelink.application.department.command.UpdateDepartmentCommand;
import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import com.carelink.application.department.exception.DepartmentNotFoundApplicationException;

public class UpdateDepartmentUseCase {
    private final DepartmentRepository repository;

    public UpdateDepartmentUseCase(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentResponse execute(UpdateDepartmentCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DepartmentNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.countryId());
        var saved = repository.save(aggregate);
        return new DepartmentResponse(
                saved.id().value(),
                saved.name(),
                saved.isoCode(),
                saved.notes(),
                saved.enabled(),
                saved.countryId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
