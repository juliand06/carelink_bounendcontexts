package com.carelink.application.contactphonenumber.usecase;

import java.time.LocalDateTime;

import com.carelink.application.contactphonenumber.exception.ContactPhoneNumberNotFoundApplicationException;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberDeletedEvent;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;

public class DeleteContactPhoneNumberUseCase {
    private final ContactPhoneNumberRepository repository;

    public DeleteContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        this.repository = repository;
    }

    public ContactPhoneNumberDeletedEvent execute(ContactPhoneNumberId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactPhoneNumberNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ContactPhoneNumberDeletedEvent(id, LocalDateTime.now());
    }
}
