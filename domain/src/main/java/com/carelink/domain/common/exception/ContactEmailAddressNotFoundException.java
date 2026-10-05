package com.carelink.domain.common.exception;

import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;

public class ContactEmailAddressNotFoundException extends RuntimeException {
    public ContactEmailAddressNotFoundException(ContactEmailAddressId id) {
        super("ContactEmailAddress not found with id: " + id.value());
    }
}
