package com.carelink.domain.specialistcredential.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialRegisteredEvent;
import com.carelink.domain.specialistcredential.event.SpecialistCredentialUpdatedEvent;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public class SpecialistCredential extends AggregateRoot {
    private final SpecialistCredentialId id;
    private AcademicProgramId academicProgramId;
    private SpecialistId specialistId;
    private String degreeTitle;
    private String institution;
    private Boolean isVerified;
    private String resolutionCode;
    private CountryId countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SpecialistCredential(
            SpecialistCredentialId id,
            AcademicProgramId academicProgramId,
            SpecialistId specialistId,
            String degreeTitle,
            String institution,
            Boolean isVerified,
            String resolutionCode,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.academicProgramId = Objects.requireNonNull(academicProgramId, "academicProgramId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.degreeTitle = Objects.requireNonNull(degreeTitle, "degreeTitle must not be null");
        this.institution = Objects.requireNonNull(institution, "institution must not be null");
        this.isVerified = Objects.requireNonNull(isVerified, "isVerified must not be null");
        this.resolutionCode = resolutionCode;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static SpecialistCredential register(
            AcademicProgramId academicProgramId,
            SpecialistId specialistId,
            String degreeTitle,
            String institution,
            Boolean isVerified,
            String resolutionCode,
            CountryId countryId) {
        SpecialistCredentialId id = SpecialistCredentialId.generate();
        LocalDateTime now = LocalDateTime.now();
        SpecialistCredential aggregate = new SpecialistCredential(
                id,
                academicProgramId,
                specialistId,
                degreeTitle,
                institution,
                isVerified,
                resolutionCode,
                countryId,
                now,
                now);
        aggregate.recordEvent(new SpecialistCredentialRegisteredEvent(id, now));
        return aggregate;
    }

    public static SpecialistCredential restore(
            SpecialistCredentialId id,
            AcademicProgramId academicProgramId,
            SpecialistId specialistId,
            String degreeTitle,
            String institution,
            Boolean isVerified,
            String resolutionCode,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new SpecialistCredential(
                id,
                academicProgramId,
                specialistId,
                degreeTitle,
                institution,
                isVerified,
                resolutionCode,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            AcademicProgramId academicProgramId,
            SpecialistId specialistId,
            String degreeTitle,
            String institution,
            Boolean isVerified,
            String resolutionCode,
            CountryId countryId) {
        this.academicProgramId = Objects.requireNonNull(academicProgramId, "academicProgramId must not be null");
        this.specialistId = Objects.requireNonNull(specialistId, "specialistId must not be null");
        this.degreeTitle = Objects.requireNonNull(degreeTitle, "degreeTitle must not be null");
        this.institution = Objects.requireNonNull(institution, "institution must not be null");
        this.isVerified = Objects.requireNonNull(isVerified, "isVerified must not be null");
        this.resolutionCode = resolutionCode;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new SpecialistCredentialUpdatedEvent(
                this.id,
                this.academicProgramId,
                this.specialistId,
                this.degreeTitle,
                this.institution,
                this.isVerified,
                this.resolutionCode,
                this.countryId,
                now));
    }

    public SpecialistCredentialId id() {
        return id;
    }

    public AcademicProgramId academicProgramId() {
        return academicProgramId;
    }

    public SpecialistId specialistId() {
        return specialistId;
    }

    public String degreeTitle() {
        return degreeTitle;
    }

    public String institution() {
        return institution;
    }

    public Boolean isVerified() {
        return isVerified;
    }

    public String resolutionCode() {
        return resolutionCode;
    }

    public CountryId countryId() {
        return countryId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
