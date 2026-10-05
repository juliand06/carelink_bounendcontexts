package com.carelink.application.senderrole.usecase;

import java.time.LocalDateTime;

import com.carelink.application.senderrole.exception.SenderRoleNotFoundApplicationException;
import com.carelink.domain.senderrole.event.SenderRoleDeletedEvent;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.domain.senderrole.port.repository.SenderRoleRepository;

public class DeleteSenderRoleUseCase {
    private final SenderRoleRepository repository;

    public DeleteSenderRoleUseCase(SenderRoleRepository repository) {
        this.repository = repository;
    }

    public SenderRoleDeletedEvent execute(SenderRoleId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new SenderRoleNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new SenderRoleDeletedEvent(id, LocalDateTime.now());
    }
}
