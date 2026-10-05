package com.carelink.application.senderrole.usecase;

import com.carelink.application.senderrole.command.RegisterSenderRoleCommand;
import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import com.carelink.domain.senderrole.model.aggregate.SenderRole;

public class RegisterSenderRoleUseCase {
    private final SenderRoleRepository repository;

    public RegisterSenderRoleUseCase(SenderRoleRepository repository) {
        this.repository = repository;
    }

    public SenderRoleResponse execute(RegisterSenderRoleCommand command) {
        SenderRole aggregate = SenderRole.register(
                command.label());
        SenderRole saved = repository.save(aggregate);
        return new SenderRoleResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
