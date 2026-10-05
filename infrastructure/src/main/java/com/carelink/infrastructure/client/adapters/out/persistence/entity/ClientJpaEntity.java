package com.carelink.infrastructure.client.adapters.out.persistence.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "clients", uniqueConstraints = {
        @UniqueConstraint(name = "uk_clients_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_clients_id_document_type_id_document_number", columnNames = {"id_document_type_id", "document_number"})
})
public class ClientJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "id_document_type_id", nullable = false)
    private UUID idDocumentTypeId;

    @Column(name = "document_number", nullable = false, length = 30)
    private String documentNumber;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", nullable = true, length = 50)
    private String middleName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "second_last_name", nullable = true, length = 50)
    private String secondLastName;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "biological_sex_id", nullable = false)
    private UUID biologicalSexId;

    @Column(name = "gender_identity_id", nullable = false)
    private UUID genderIdentityId;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "mobile_phone", nullable = true, length = 30)
    private String mobilePhone;

    @Column(name = "home_address", nullable = true, length = 250)
    private String homeAddress;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = true)
    private UUID createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", nullable = true)
    private UUID updatedBy;

    @Column(name = "municipality_id", nullable = false)
    private UUID municipalityId;

    public ClientJpaEntity() { }

    public ClientJpaEntity(
            UUID id,
            UUID idDocumentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            UUID biologicalSexId,
            UUID genderIdentityId,
            String email,
            String mobilePhone,
            String homeAddress,
            Boolean active,
            LocalDateTime createdAt,
            UUID createdBy,
            LocalDateTime updatedAt,
            UUID updatedBy,
            UUID municipalityId) {
        this.id = id;
        this.idDocumentTypeId = idDocumentTypeId;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.birthDate = birthDate;
        this.biologicalSexId = biologicalSexId;
        this.genderIdentityId = genderIdentityId;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.homeAddress = homeAddress;
        this.active = active;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
        this.municipalityId = municipalityId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getIdDocumentTypeId() { return idDocumentTypeId; }
    public void setIdDocumentTypeId(UUID idDocumentTypeId) { this.idDocumentTypeId = idDocumentTypeId; }
    public String getDocumentNumber() { return documentNumber; }
    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getSecondLastName() { return secondLastName; }
    public void setSecondLastName(String secondLastName) { this.secondLastName = secondLastName; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public UUID getBiologicalSexId() { return biologicalSexId; }
    public void setBiologicalSexId(UUID biologicalSexId) { this.biologicalSexId = biologicalSexId; }
    public UUID getGenderIdentityId() { return genderIdentityId; }
    public void setGenderIdentityId(UUID genderIdentityId) { this.genderIdentityId = genderIdentityId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMobilePhone() { return mobilePhone; }
    public void setMobilePhone(String mobilePhone) { this.mobilePhone = mobilePhone; }
    public String getHomeAddress() { return homeAddress; }
    public void setHomeAddress(String homeAddress) { this.homeAddress = homeAddress; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public UUID getCreatedBy() { return createdBy; }
    public void setCreatedBy(UUID createdBy) { this.createdBy = createdBy; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public UUID getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(UUID updatedBy) { this.updatedBy = updatedBy; }
    public UUID getMunicipalityId() { return municipalityId; }
    public void setMunicipalityId(UUID municipalityId) { this.municipalityId = municipalityId; }
}
