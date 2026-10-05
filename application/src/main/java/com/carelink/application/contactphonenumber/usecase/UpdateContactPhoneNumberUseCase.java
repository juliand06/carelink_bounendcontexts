package com.carelink.application.contactphonenumber.usecase;

import com.carelink.application.contactphonenumber.command.UpdateContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.application.contactphonenumber.exception.ContactPhoneNumberNotFoundApplicationException;

public class UpdateContactPhoneNumberUseCase {
    private final ContactPhoneNumberRepository repository;

    public UpdateContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        this.repository = repository;
    }

    public ContactPhoneNumberResponse execute(UpdateContactPhoneNumberCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ContactPhoneNumberNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.trustedContactId(),
                command.phoneNumber());
        var saved = repository.save(aggregate);
        return new ContactPhoneNumberResponse(
                saved.id().value(),
                saved.trustedContactId().value(),
                saved.phoneNumber(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
