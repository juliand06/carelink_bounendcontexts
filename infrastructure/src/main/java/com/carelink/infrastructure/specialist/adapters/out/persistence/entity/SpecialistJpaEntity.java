package com.carelink.infrastructure.specialist.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "specialists", uniqueConstraints = {
        @UniqueConstraint(name = "uk_specialists_professional_license", columnNames = "professional_license"),
        @UniqueConstraint(name = "uk_specialists_id_document_type_id_document_number", columnNames = {"id_document_type_id", "document_number"})
})
public class SpecialistJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "id_document_type_id", nullable = false)
    private UUID idDocumentTypeId;

    @Column(name = "document_number", nullable = false, length = 30)
    private String documentNumber;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column(name = "specialist_role_id", nullable = false)
    private UUID specialistRoleId;

    @Column(name = "professional_license", nullable = false, length = 100)
    private String professionalLicense;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "municipality_id", nullable = false)
    private UUID municipalityId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public SpecialistJpaEntity() { }

    public SpecialistJpaEntity(
            UUID id,
            UUID idDocumentTypeId,
            String documentNumber,
            String firstName,
            String lastName,
            UUID specialistRoleId,
            String professionalLicense,
            Boolean active,
            UUID municipalityId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.idDocumentTypeId = idDocumentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialistRoleId = specialistRoleId;
        this.professionalLicense = professionalLicense;
        this.active = active;
        this.municipalityId = municipalityId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getIdDocumentTypeId() { return idDocumentTypeId; }
    public void setIdDocumentTypeId(UUID idDocumentTypeId) { this.idDocumentTypeId = idDocumentTypeId; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public UUID getSpecialistRoleId() { return specialistRoleId; }
    public void setSpecialistRoleId(UUID specialistRoleId) { this.specialistRoleId = specialistRoleId; }
    public String getProfessionalLicense() { return professionalLicense; }
    public void setProfessionalLicense(String professionalLicense) { this.professionalLicense = professionalLicense; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public UUID getMunicipalityId() { return municipalityId; }
    public void setMunicipalityId(UUID municipalityId) { this.municipalityId = municipalityId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
