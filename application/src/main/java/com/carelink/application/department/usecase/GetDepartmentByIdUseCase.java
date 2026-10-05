package com.carelink.application.department.usecase;

import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.domain.department.port.repository.DepartmentRepository;
import com.carelink.application.department.exception.DepartmentNotFoundApplicationException;
import com.carelink.domain.department.model.valueobject.DepartmentId;

public class GetDepartmentByIdUseCase {
    private final DepartmentRepository repository;

    public GetDepartmentByIdUseCase(DepartmentRepository repository) {
        this.repository = repository;
    }

    public DepartmentResponse execute(DepartmentId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundApplicationException(id.value().toString()));
        return new DepartmentResponse(
                aggregate.id().value(),
                aggregate.name(),
                aggregate.isoCode(),
                aggregate.notes(),
                aggregate.enabled(),
                aggregate.countryId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
