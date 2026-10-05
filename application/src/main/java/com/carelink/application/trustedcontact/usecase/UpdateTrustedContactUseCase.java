package com.carelink.application.trustedcontact.usecase;

import com.carelink.application.trustedcontact.command.UpdateTrustedContactCommand;
import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import com.carelink.application.trustedcontact.exception.TrustedContactNotFoundApplicationException;

public class UpdateTrustedContactUseCase {
    private final TrustedContactRepository repository;

    public UpdateTrustedContactUseCase(TrustedContactRepository repository) {
        this.repository = repository;
    }

    public TrustedContactResponse execute(UpdateTrustedContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TrustedContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.fullName(),
                command.relationshipTypeId(),
                command.isPrimaryContact(),
                command.notes());
        var saved = repository.save(aggregate);
        return new TrustedContactResponse(
                saved.id().value(),
                saved.fullName(),
                saved.relationshipTypeId().value(),
                saved.isPrimaryContact(),
                saved.notes(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
