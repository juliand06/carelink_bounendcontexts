package com.carelink.domain.clientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.event.ClientAllergyRegisteredEvent;
import com.carelink.domain.clientallergy.event.ClientAllergyUpdatedEvent;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class ClientAllergy extends AggregateRoot {
    private final ClientAllergyId id;
    private ClientId clientId;
    private String allergen;
    private String reactionDescription;
    private String severityLevel;
    private Boolean active;
    private final LocalDateTime registeredAt;
    private SpecialistId registeredBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClientAllergy(
            ClientAllergyId id,
            ClientId clientId,
            String allergen,
            String reactionDescription,
            String severityLevel,
            Boolean active,
            LocalDateTime registeredAt,
            SpecialistId registeredBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.allergen = Objects.requireNonNull(allergen, "allergen must not be null");
        this.reactionDescription = reactionDescription;
        this.severityLevel = severityLevel;
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.registeredAt = Objects.requireNonNull(registeredAt, "registeredAt must not be null");
        this.registeredBy = Objects.requireNonNull(registeredBy, "registeredBy must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ClientAllergy register(
            ClientId clientId,
            String allergen,
            String reactionDescription,
            String severityLevel,
            Boolean active,
            SpecialistId registeredBy) {
        ClientAllergyId id = ClientAllergyId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClientAllergy aggregate = new ClientAllergy(
                id,
                clientId,
                allergen,
                reactionDescription,
                severityLevel,
                active,
                now,
                registeredBy,
                now,
                now);
        aggregate.recordEvent(new ClientAllergyRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClientAllergy restore(
            ClientAllergyId id,
            ClientId clientId,
            String allergen,
            String reactionDescription,
            String severityLevel,
            Boolean active,
            LocalDateTime registeredAt,
            SpecialistId registeredBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClientAllergy(
                id,
                clientId,
                allergen,
                reactionDescription,
                severityLevel,
                active,
                registeredAt,
                registeredBy,
                createdAt,
                updatedAt);
    }

    public void update(
            ClientId clientId,
            String allergen,
            String reactionDescription,
            String severityLevel,
            Boolean active,
            SpecialistId registeredBy) {
        this.clientId = Objects.requireNonNull(clientId, "clientId must not be null");
        this.allergen = Objects.requireNonNull(allergen, "allergen must not be null");
        this.reactionDescription = reactionDescription;
        this.severityLevel = severityLevel;
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.registeredBy = Objects.requireNonNull(registeredBy, "registeredBy must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ClientAllergyUpdatedEvent(
                this.id,
                this.clientId,
                this.allergen,
                this.reactionDescription,
                this.severityLevel,
                this.active,
                this.registeredBy,
                now));
    }

    public ClientAllergyId id() {
        return id;
    }

    public ClientId clientId() {
        return clientId;
    }

    public String allergen() {
        return allergen;
    }

    public String reactionDescription() {
        return reactionDescription;
    }

    public String severityLevel() {
        return severityLevel;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime registeredAt() {
        return registeredAt;
    }

    public SpecialistId registeredBy() {
        return registeredBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
