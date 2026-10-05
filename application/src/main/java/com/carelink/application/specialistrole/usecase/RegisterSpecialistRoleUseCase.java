package com.carelink.application.specialistrole.usecase;

import com.carelink.application.specialistrole.command.RegisterSpecialistRoleCommand;
import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.domain.specialistrole.port.repository.SpecialistRoleRepository;
import com.carelink.domain.specialistrole.model.aggregate.SpecialistRole;

public class RegisterSpecialistRoleUseCase {
    private final SpecialistRoleRepository repository;

    public RegisterSpecialistRoleUseCase(SpecialistRoleRepository repository) {
        this.repository = repository;
    }

    public SpecialistRoleResponse execute(RegisterSpecialistRoleCommand command) {
        SpecialistRole aggregate = SpecialistRole.register(
                command.title());
        SpecialistRole saved = repository.save(aggregate);
        return new SpecialistRoleResponse(
                saved.id().value(),
                saved.title(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
