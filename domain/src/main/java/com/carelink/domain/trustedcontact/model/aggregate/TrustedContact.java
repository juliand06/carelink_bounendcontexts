package com.carelink.domain.trustedcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.event.TrustedContactRegisteredEvent;
import com.carelink.domain.trustedcontact.event.TrustedContactUpdatedEvent;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public class TrustedContact extends AggregateRoot {
    private final TrustedContactId id;
    private String fullName;
    private ContactRelationshipTypeId relationshipTypeId;
    private Boolean isPrimaryContact;
    private String notes;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TrustedContact(
            TrustedContactId id,
            String fullName,
            ContactRelationshipTypeId relationshipTypeId,
            Boolean isPrimaryContact,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
        this.relationshipTypeId = Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        this.isPrimaryContact = Objects.requireNonNull(isPrimaryContact, "isPrimaryContact must not be null");
        this.notes = notes;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static TrustedContact register(
            String fullName,
            ContactRelationshipTypeId relationshipTypeId,
            Boolean isPrimaryContact,
            String notes) {
        TrustedContactId id = TrustedContactId.generate();
        LocalDateTime now = LocalDateTime.now();
        TrustedContact aggregate = new TrustedContact(
                id,
                fullName,
                relationshipTypeId,
                isPrimaryContact,
                notes,
                now,
                now);
        aggregate.recordEvent(new TrustedContactRegisteredEvent(id, now));
        return aggregate;
    }

    public static TrustedContact restore(
            TrustedContactId id,
            String fullName,
            ContactRelationshipTypeId relationshipTypeId,
            Boolean isPrimaryContact,
            String notes,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TrustedContact(
                id,
                fullName,
                relationshipTypeId,
                isPrimaryContact,
                notes,
                createdAt,
                updatedAt);
    }

    public void update(
            String fullName,
            ContactRelationshipTypeId relationshipTypeId,
            Boolean isPrimaryContact,
            String notes) {
        this.fullName = Objects.requireNonNull(fullName, "fullName must not be null");
        this.relationshipTypeId = Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        this.isPrimaryContact = Objects.requireNonNull(isPrimaryContact, "isPrimaryContact must not be null");
        this.notes = notes;
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new TrustedContactUpdatedEvent(
                this.id,
                this.fullName,
                this.relationshipTypeId,
                this.isPrimaryContact,
                this.notes,
                now));
    }

    public TrustedContactId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public ContactRelationshipTypeId relationshipTypeId() {
        return relationshipTypeId;
    }

    public Boolean isPrimaryContact() {
        return isPrimaryContact;
    }

    public String notes() {
        return notes;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
