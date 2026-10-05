package com.carelink.application.contactemailaddress.usecase;

import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;
import com.carelink.application.contactemailaddress.exception.ContactEmailAddressNotFoundApplicationException;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;

public class GetContactEmailAddressByIdUseCase {
    private final ContactEmailAddressRepository repository;

    public GetContactEmailAddressByIdUseCase(ContactEmailAddressRepository repository) {
        this.repository = repository;
    }

    public ContactEmailAddressResponse execute(ContactEmailAddressId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactEmailAddressNotFoundApplicationException(id.value().toString()));
        return new ContactEmailAddressResponse(
                aggregate.id().value(),
                aggregate.trustedContactId().value(),
                aggregate.emailAddress(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
