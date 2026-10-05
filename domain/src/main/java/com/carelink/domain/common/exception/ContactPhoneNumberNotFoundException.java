package com.carelink.domain.common.exception;

import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;

public class ContactPhoneNumberNotFoundException extends RuntimeException {
    public ContactPhoneNumberNotFoundException(ContactPhoneNumberId id) {
        super("ContactPhoneNumber not found with id: " + id.value());
    }
}
