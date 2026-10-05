package com.carelink.infrastructure.specialistcredential.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "specialist_credentials")
public class SpecialistCredentialJpaEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "academic_program_id", nullable = false)
    private UUID academicProgramId;

    @Column(name = "specialist_id", nullable = false)
    private UUID specialistId;

    @Column(name = "degree_title", nullable = false, length = 100)
    private String degreeTitle;

    @Column(name = "institution", nullable = false, length = 100)
    private String institution;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

    @Column(name = "resolution_code", nullable = true, length = 60)
    private String resolutionCode;

    @Column(name = "country_id", nullable = false)
    private UUID countryId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public SpecialistCredentialJpaEntity() { }

    public SpecialistCredentialJpaEntity(
            UUID id,
            UUID academicProgramId,
            UUID specialistId,
            String degreeTitle,
            String institution,
            Boolean isVerified,
            String resolutionCode,
            UUID countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.academicProgramId = academicProgramId;
        this.specialistId = specialistId;
        this.degreeTitle = degreeTitle;
        this.institution = institution;
        this.isVerified = isVerified;
        this.resolutionCode = resolutionCode;
        this.countryId = countryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getAcademicProgramId() { return academicProgramId; }
    public void setAcademicProgramId(UUID academicProgramId) { this.academicProgramId = academicProgramId; }
    public UUID getSpecialistId() { return specialistId; }
    public void setSpecialistId(UUID specialistId) { this.specialistId = specialistId; }
    public String getDegreeTitle() { return degreeTitle; }
    public void setDegreeTitle(String degreeTitle) { this.degreeTitle = degreeTitle; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    public String getResolutionCode() { return resolutionCode; }
    public void setResolutionCode(String resolutionCode) { this.resolutionCode = resolutionCode; }
    public UUID getCountryId() { return countryId; }
    public void setCountryId(UUID countryId) { this.countryId = countryId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
