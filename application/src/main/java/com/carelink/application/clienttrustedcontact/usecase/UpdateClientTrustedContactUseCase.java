package com.carelink.application.clienttrustedcontact.usecase;

import com.carelink.application.clienttrustedcontact.command.UpdateClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.application.clienttrustedcontact.exception.ClientTrustedContactNotFoundApplicationException;

public class UpdateClientTrustedContactUseCase {
    private final ClientTrustedContactRepository repository;

    public UpdateClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        this.repository = repository;
    }

    public ClientTrustedContactResponse execute(UpdateClientTrustedContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClientTrustedContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.clientId(),
                command.trustedContactId());
        var saved = repository.save(aggregate);
        return new ClientTrustedContactResponse(
                saved.id().value(),
                saved.clientId().value(),
                saved.trustedContactId().value(),
                saved.createdAt());
    }
}
