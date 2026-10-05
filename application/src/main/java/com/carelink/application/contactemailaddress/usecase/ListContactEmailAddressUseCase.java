package com.carelink.application.contactemailaddress.usecase;

import java.util.List;

import com.carelink.application.contactemailaddress.dto.ContactEmailAddressResponse;
import com.carelink.domain.contactemailaddress.port.repository.ContactEmailAddressRepository;

public class ListContactEmailAddressUseCase {
    private final ContactEmailAddressRepository repository;

    public ListContactEmailAddressUseCase(ContactEmailAddressRepository repository) {
        this.repository = repository;
    }

    public List<ContactEmailAddressResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ContactEmailAddressResponse(
                        aggregate.id().value(),
                        aggregate.trustedContactId().value(),
                        aggregate.emailAddress(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
