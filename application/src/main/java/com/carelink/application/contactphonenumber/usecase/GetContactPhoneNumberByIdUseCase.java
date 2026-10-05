package com.carelink.application.contactphonenumber.usecase;

import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;
import com.carelink.application.contactphonenumber.exception.ContactPhoneNumberNotFoundApplicationException;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;

public class GetContactPhoneNumberByIdUseCase {
    private final ContactPhoneNumberRepository repository;

    public GetContactPhoneNumberByIdUseCase(ContactPhoneNumberRepository repository) {
        this.repository = repository;
    }

    public ContactPhoneNumberResponse execute(ContactPhoneNumberId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactPhoneNumberNotFoundApplicationException(id.value().toString()));
        return new ContactPhoneNumberResponse(
                aggregate.id().value(),
                aggregate.trustedContactId().value(),
                aggregate.phoneNumber(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
