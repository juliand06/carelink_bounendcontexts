package com.carelink.application.contactemailaddress.usecase;

import com.carelink.application.contactemailaddress.command.RegisterContactEmailAddressCommand;
import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;

public class RegisterContactEmailAddressUseCase {
    private final ContactEmailAddressRepository repository;

    public RegisterContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        this.repository = repository;
    }

    public ContactEmailAddressResponse execute(RegisterContactEmailAddressCommand command) {
        ContactEmailAddress aggregate = ContactEmailAddress.register(
                command.trustedContactId(),
                command.emailAddress());
        ContactEmailAddress saved = repository.save(aggregate);
        return new ContactEmailAddressResponse(
                saved.id().value(),
                saved.trustedContactId().value(),
                saved.emailAddress(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
