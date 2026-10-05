package com.carelink.application.specialistrole.usecase;

import java.util.List;

import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;

public class ListSpecialistRoleUseCase {
    private final SpecialistRoleRepository repository;

    public ListSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        this.repository = repository;
    }

    public List<SpecialistRoleResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SpecialistRoleResponse(
                        aggregate.id().value(),
                        aggregate.title(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
