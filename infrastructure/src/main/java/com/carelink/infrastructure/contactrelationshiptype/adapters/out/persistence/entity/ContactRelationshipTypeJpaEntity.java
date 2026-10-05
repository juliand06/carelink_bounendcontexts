package com.carelink.infrastructure.contactrelationshiptype.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "contact_relationship_types", uniqueConstraints = {
        @UniqueConstraint(name = "uk_contact_relationship_types_label", columnNames = "label")
})
public class ContactRelationshipTypeJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    public ContactRelationshipTypeJpaEntity() { }

    public ContactRelationshipTypeJpaEntity(
            UUID id,
            String label) {
        this.id = id;
        this.label = label;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
}
