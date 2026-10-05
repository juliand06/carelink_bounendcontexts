package com.carelink.application.contactphonenumber.usecase;

import com.carelink.application.contactphonenumber.command.RegisterContactPhoneNumberCommand;
import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;

public class RegisterContactPhoneNumberUseCase {
    private final ContactPhoneNumberRepository repository;

    public RegisterContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        this.repository = repository;
    }

    public ContactPhoneNumberResponse execute(RegisterContactPhoneNumberCommand command) {
        ContactPhoneNumber aggregate = ContactPhoneNumber.register(
                command.trustedContactId(),
                command.phoneNumber());
        ContactPhoneNumber saved = repository.save(aggregate);
        return new ContactPhoneNumberResponse(
                saved.id().value(),
                saved.trustedContactId().value(),
                saved.phoneNumber(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
