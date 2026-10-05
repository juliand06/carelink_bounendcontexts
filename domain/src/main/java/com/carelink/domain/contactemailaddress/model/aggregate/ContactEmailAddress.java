package com.carelink.domain.contactemailaddress.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressRegisteredEvent;
import com.carelink.domain.contactemailaddress.event.ContactEmailAddressUpdatedEvent;
import com.carelink.domain.contactemailaddress.model.valueobject.ContactEmailAddressId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class ContactEmailAddress extends AggregateRoot {
    private final ContactEmailAddressId id;
    private TrustedContactId trustedContactId;
    private String emailAddress;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ContactEmailAddress(
            ContactEmailAddressId id,
            TrustedContactId trustedContactId,
            String emailAddress,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        this.emailAddress = Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ContactEmailAddress register(
            TrustedContactId trustedContactId,
            String emailAddress) {
        ContactEmailAddressId id = ContactEmailAddressId.generate();
        LocalDateTime now = LocalDateTime.now();
        ContactEmailAddress aggregate = new ContactEmailAddress(
                id,
                trustedContactId,
                emailAddress,
                now,
                now);
        aggregate.recordEvent(new ContactEmailAddressRegisteredEvent(id, now));
        return aggregate;
    }

    public static ContactEmailAddress restore(
            ContactEmailAddressId id,
            TrustedContactId trustedContactId,
            String emailAddress,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ContactEmailAddress(
                id,
                trustedContactId,
                emailAddress,
                createdAt,
                updatedAt);
    }

    public void update(
            TrustedContactId trustedContactId,
            String emailAddress) {
        this.trustedContactId = Objects.requireNonNull(trustedContactId, "trustedContactId must not be null");
        this.emailAddress = Objects.requireNonNull(emailAddress, "emailAddress must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ContactEmailAddressUpdatedEvent(
                this.id,
                this.trustedContactId,
                this.emailAddress,
                now));
    }

    public ContactEmailAddressId id() {
        return id;
    }

    public TrustedContactId trustedContactId() {
        return trustedContactId;
    }

    public String emailAddress() {
        return emailAddress;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
