package com.carelink.application.contactphonenumber.usecase;

import java.util.List;

import com.carelink.application.contactphonenumber.dto.ContactPhoneNumberResponse;
import com.carelink.domain.contactphonenumber.port.repository.ContactPhoneNumberRepository;

public class ListContactPhoneNumberUseCase {
    private final ContactPhoneNumberRepository repository;

    public ListContactPhoneNumberUseCase(ContactPhoneNumberRepository repository) {
        this.repository = repository;
    }

    public List<ContactPhoneNumberResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ContactPhoneNumberResponse(
                        aggregate.id().value(),
                        aggregate.trustedContactId().value(),
                        aggregate.phoneNumber(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
