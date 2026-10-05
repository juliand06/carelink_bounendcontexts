package com.carelink.domain.contactphonenumber.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactphonenumber.model.aggregate.ContactPhoneNumber;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;

public interface ContactPhoneNumberRepository {
    ContactPhoneNumber save(ContactPhoneNumber aggregate);

    Optional<ContactPhoneNumber> findById(ContactPhoneNumberId id);

    List<ContactPhoneNumber> findAll();

    void delete(ContactPhoneNumber aggregate);
}
