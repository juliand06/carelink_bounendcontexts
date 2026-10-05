package com.carelink.application.contactemailaddress.usecase;

import com.carelink.application.contactemailaddress.command.UpdateContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.application.contactemailaddress.exception.ContactEmailAddressNotFoundApplicationException;

public class UpdateContactEmailAddressUseCase {
    private final ContactEmailAddressRepository repository;

    public UpdateContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        this.repository = repository;
    }

    public ContactEmailAddressResponse execute(UpdateContactEmailAddressCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ContactEmailAddressNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.trustedContactId(),
                command.emailAddress());
        var saved = repository.save(aggregate);
        return new ContactEmailAddressResponse(
                saved.id().value(),
                saved.trustedContactId().value(),
                saved.emailAddress(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
