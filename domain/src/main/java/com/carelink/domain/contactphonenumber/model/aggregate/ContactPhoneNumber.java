package com.carelink.domain.contactphonenumber.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberRegisteredEvent;
import com.carelink.domain.contactphonenumber.event.ContactPhoneNumberUpdatedEvent;
import com.carelink.domain.contactphonenumber.model.valueobject.ContactPhoneNumberId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class ContactPhoneNumber extends AggregateRoot {
    private final ContactPhoneNumberId id;
    private TrustedContactId trustedContactId;
    private String phoneNumber;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ContactPhoneNumber(
            ContactPhoneNumberId id,
            TrustedContactId trustedContactId,
            String phoneNumber,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ContactPhoneNumber register(
            TrustedContactId trustedContactId,
            String phoneNumber) {
        ContactPhoneNumberId id = ContactPhoneNumberId.generate();
        LocalDateTime now = LocalDateTime.now();
        ContactPhoneNumber aggregate = new ContactPhoneNumber(
                id,
                trustedContactId,
                phoneNumber,
                now,
                now);
        aggregate.recordEvent(new ContactPhoneNumberRegisteredEvent(id, now));
        return aggregate;
    }

    public static ContactPhoneNumber restore(
            ContactPhoneNumberId id,
            TrustedContactId trustedContactId,
            String phoneNumber,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ContactPhoneNumber(
                id,
                trustedContactId,
                phoneNumber,
                createdAt,
                updatedAt);
    }

    public void update(
            TrustedContactId trustedContactId,
            String phoneNumber) {
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        this.phoneNumber = Objects.requireNonNull(phoneNumber, "phoneNumber must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ContactPhoneNumberUpdatedEvent(
                this.id,
                this.trustedContactId,
                this.phoneNumber,
                now));
    }

    public ContactPhoneNumberId id() {
        return id;
    }

    public TrustedContactId trustedContactId() {
        return trustedContactId;
    }

    public String phoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
