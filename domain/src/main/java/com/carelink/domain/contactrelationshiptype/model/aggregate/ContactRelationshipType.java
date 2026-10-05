package com.carelink.domain.contactrelationshiptype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeRegisteredEvent;
import com.carelink.domain.contactrelationshiptype.event.ContactRelationshipTypeUpdatedEvent;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;

public class ContactRelationshipType extends AggregateRoot {
    private final ContactRelationshipTypeId id;
    private String label;

    private ContactRelationshipType(
            ContactRelationshipTypeId id,
            String label) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.label = Objects.requireNonNull(label, "label must not be null");
    }

    public static ContactRelationshipType register(
            String label) {
        ContactRelationshipTypeId id = ContactRelationshipTypeId.generate();
        LocalDateTime now = LocalDateTime.now();
        ContactRelationshipType aggregate = new ContactRelationshipType(
                id,
                label);
        aggregate.recordEvent(new ContactRelationshipTypeRegisteredEvent(id, now));
        return aggregate;
    }

    public static ContactRelationshipType restore(
            ContactRelationshipTypeId id,
            String label) {
        return new ContactRelationshipType(
                id,
                label);
    }

    public void update(
            String label) {
        this.label = Objects.requireNonNull(label, "label must not be null");
        LocalDateTime now = LocalDateTime.now();
        recordEvent(new ContactRelationshipTypeUpdatedEvent(
                this.id,
                this.label,
                now));
    }

    public ContactRelationshipTypeId id() {
        return id;
    }

    public String label() {
        return label;
    }
}
