package com.carelink.application.department.usecase;

import java.util.List;

import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.domain.department.port.repository.DepartmentRepository;

public class ListDepartmentUseCase {
    private final DepartmentRepository repository;

    public ListDepartmentUseCase(DepartmentRepository repository) {
        this.repository = repository;
    }

    public List<DepartmentResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new DepartmentResponse(
                        aggregate.id().value(),
                        aggregate.name(),
                        aggregate.isoCode(),
                        aggregate.notes(),
                        aggregate.enabled(),
                        aggregate.countryId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
