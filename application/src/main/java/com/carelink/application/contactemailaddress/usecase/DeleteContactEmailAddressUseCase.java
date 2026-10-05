package com.carelink.application.contactemailaddress.usecase;

import java.time.LocalDateTime;

import com.carelink.application.contactemailaddress.exception.ContactEmailAddressNotFoundApplicationException;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressDeletedEvent;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;

public class DeleteContactEmailAddressUseCase {
    private final ContactEmailAddressRepository repository;

    public DeleteContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        this.repository = repository;
    }

    public ContactEmailAddressDeletedEvent execute(ContactEmailAddressId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactEmailAddressNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ContactEmailAddressDeletedEvent(id, LocalDateTime.now());
    }
}
