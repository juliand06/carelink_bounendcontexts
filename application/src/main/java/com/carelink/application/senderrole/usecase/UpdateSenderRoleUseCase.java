package com.carelink.application.senderrole.usecase;

import com.carelink.application.senderrole.command.UpdateSenderRoleCommand;
import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import com.carelink.application.senderrole.exception.SenderRoleNotFoundApplicationException;

public class UpdateSenderRoleUseCase {
    private final SenderRoleRepository repository;

    public UpdateSenderRoleUseCase(SenderRoleRepository repository) {
        this.repository = repository;
    }

    public SenderRoleResponse execute(UpdateSenderRoleCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new SenderRoleNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new SenderRoleResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
