package com.carelink.application.department.usecase;

import com.carelink.application.department.command.RegisterDepartmentCommand;
import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import com.carelink.domain.department.model.aggregate.Department;

public class RegisterDepartmentUseCase {
    private final DepartmentRepository repository;

    public RegisterDepartmentUseCase(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentResponse execute(RegisterDepartmentCommand command) {
        Department aggregate = Department.register(
                command.name(),
                command.isoCode(),
                command.notes(),
                command.enabled(),
                command.countryId());
        Department saved = repository.save(aggregate);
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
