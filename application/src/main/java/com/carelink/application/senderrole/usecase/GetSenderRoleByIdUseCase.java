package com.carelink.application.senderrole.usecase;

import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;
import com.carelink.application.senderrole.exception.SenderRoleNotFoundApplicationException;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public class GetSenderRoleByIdUseCase {
    private final SenderRoleRepository repository;

    public GetSenderRoleByIdUseCase(SenderRoleRepository repository) {
        this.repository = repository;
    }

    public SenderRoleResponse execute(SenderRoleId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SenderRoleNotFoundApplicationException(id.value().toString()));
        return new SenderRoleResponse(
                aggregate.id().value(),
                aggregate.label(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
