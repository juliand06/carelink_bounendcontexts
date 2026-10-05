package com.carelink.application.clienttrustedcontact.usecase;

import com.carelink.application.clienttrustedcontact.command.RegisterClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.domain.clienttrustedcontact.port.repository.ClientTrustedContactRepository;
import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;

public class RegisterClientTrustedContactUseCase {
    private final ClientTrustedContactRepository repository;

    public RegisterClientTrustedContactUseCase(ClientTrustedContactRepository repository) {
        this.repository = repository;
    }

    public ClientTrustedContactResponse execute(RegisterClientTrustedContactCommand command) {
        ClientTrustedContact aggregate = ClientTrustedContact.register(
                command.clientId(),
                command.trustedContactId());
        ClientTrustedContact saved = repository.save(aggregate);
        return new ClientTrustedContactResponse(
                saved.id().value(),
                saved.clientId().value(),
                saved.trustedContactId().value(),
                saved.createdAt());
    }
}
