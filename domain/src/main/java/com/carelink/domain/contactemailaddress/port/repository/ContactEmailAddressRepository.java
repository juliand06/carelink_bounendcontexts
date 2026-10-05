package com.carelink.domain.contactemailaddress.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.contactemailaddress.model.aggregate.ContactEmailAddress;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;

public interface ContactEmailAddressRepository {
    ContactEmailAddress save(ContactEmailAddress aggregate);

    Optional<ContactEmailAddress> findById(ContactEmailAddressId id);

    List<ContactEmailAddress> findAll();

    void delete(ContactEmailAddress aggregate);
}
