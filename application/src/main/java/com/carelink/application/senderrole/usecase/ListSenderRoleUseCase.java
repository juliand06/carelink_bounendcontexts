package com.carelink.application.senderrole.usecase;

import java.util.List;

import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;

public class ListSenderRoleUseCase {
    private final SenderRoleRepository repository;

    public ListSenderRoleUseCase(SenderRoleRepository repository) {
        this.repository = repository;
    }

    public List<SenderRoleResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new SenderRoleResponse(
                        aggregate.id().value(),
                        aggregate.label(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
