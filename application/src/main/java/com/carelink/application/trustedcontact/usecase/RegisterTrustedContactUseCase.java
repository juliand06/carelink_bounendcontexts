package com.carelink.application.trustedcontact.usecase;

import com.carelink.application.trustedcontact.command.RegisterTrustedContactCommand;
import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.domain.trustedcontact.port.repository.TrustedContactRepository;
import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;

public class RegisterTrustedContactUseCase {
    private final TrustedContactRepository repository;

    public RegisterTrustedContactUseCase(TrustedContactRepository repository) {
        this.repository = repository;
    }

    public TrustedContactResponse execute(RegisterTrustedContactCommand command) {
        TrustedContact aggregate = TrustedContact.register(
                command.fullName(),
                command.relationshipTypeId(),
                command.isPrimaryContact(),
                command.notes());
        TrustedContact saved = repository.save(aggregate);
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
