package com.carelink.domain.specialist.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.event.SpecialistRegisteredEvent;
import com.carelink.domain.specialist.event.SpecialistUpdatedEvent;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;

public class Specialist extends AggregateRoot {
    private final SpecialistId id;
    private IdDocumentTypeId idDocumentTypeId;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private SpecialistRoleId specialistRoleId;
    private String professionalLicense;
    private Boolean active;
    private MunicipalityId municipalityId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Specialist(
            SpecialistId id,
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            SpecialistRoleId specialistRoleId,
            String professionalLicense,
            Boolean active,
            MunicipalityId municipalityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.idDocumentTypeId = Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.specialistRoleId = Objects.requireNonNull(specialistRoleId, "specialistRoleId must not be null");
        this.professionalLicense = Objects.requireNonNull(professionalLicense, "professionalLicense must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.municipalityId = Objects.requireNonNull(municipalityId, "municipalityId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Specialist register(
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            SpecialistRoleId specialistRoleId,
            String professionalLicense,
            Boolean active,
            MunicipalityId municipalityId) {
        SpecialistId id = SpecialistId.generate();
        LocalDateTime now = LocalDateTime.now();
        Specialist aggregate = new Specialist(
                id,
                idDocumentTypeId,
                documentNumber,
                firstName,
                lastName,
                specialistRoleId,
                professionalLicense,
                active,
                municipalityId,
                now,
                now);
        aggregate.recordEvent(new SpecialistRegisteredEvent(id, now));
        return aggregate;
    }

    public static Specialist restore(
            SpecialistId id,
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            SpecialistRoleId specialistRoleId,
            String professionalLicense,
            Boolean active,
            MunicipalityId municipalityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Specialist(
                id,
                idDocumentTypeId,
                documentNumber,
                firstName,
                lastName,
                specialistRoleId,
                professionalLicense,
                active,
                municipalityId,
                createdAt,
                updatedAt);
    }

    public void update(
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            SpecialistRoleId specialistRoleId,
            String professionalLicense,
            Boolean active,
            MunicipalityId municipalityId) {
        this.idDocumentTypeId = Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.specialistRoleId = Objects.requireNonNull(specialistRoleId, "specialistRoleId must not be null");
        this.professionalLicense = Objects.requireNonNull(professionalLicense, "professionalLicense must not be null");
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.municipalityId = Objects.requireNonNull(municipalityId, "municipalityId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SpecialistUpdatedEvent(
                this.id,
                this.idDocumentTypeId,
                this.documentNumber,
                this.firstName,
                this.lastName,
                this.specialistRoleId,
                this.professionalLicense,
                this.active,
                this.municipalityId,
                now));
    }

    public SpecialistId id() {
        return id;
    }

    public IdDocumentTypeId idDocumentTypeId() {
        return idDocumentTypeId;
    }

    public String documentNumber() {
        return documentNumber;
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public SpecialistRoleId specialistRoleId() {
        return specialistRoleId;
    }

    public String professionalLicense() {
        return professionalLicense;
    }

    public Boolean active() {
        return active;
    }

    public MunicipalityId municipalityId() {
        return municipalityId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
