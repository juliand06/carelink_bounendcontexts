package com.carelink.domain.client.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.client.event.ClientRegisteredEvent;
import com.carelink.domain.client.event.ClientUpdatedEvent;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.common.model.AggregateRoot;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;

public class Client extends AggregateRoot {
    private final ClientId id;
    private IdDocumentTypeId idDocumentTypeId;
    private String documentNumber;
    private String firstName;
    private String middleName;
    private String lastName;
    private String secondLastName;
    private LocalDate birthDate;
    private BiologicalSexId biologicalSexId;
    private BiologicalSexId genderIdentityId;
    private String email;
    private String mobilePhone;
    private String homeAddress;
    private Boolean active;
    private final LocalDateTime createdAt;
    private final SpecialistId createdBy;
    private LocalDateTime updatedAt;
    private SpecialistId updatedBy;
    private MunicipalityId municipalityId;

    private Client(
            ClientId id,
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            BiologicalSexId biologicalSexId,
            BiologicalSexId genderIdentityId,
            String email,
            String mobilePhone,
            String homeAddress,
            Boolean active,
            LocalDateTime createdAt,
            SpecialistId createdBy,
            LocalDateTime updatedAt,
            SpecialistId updatedBy,
            MunicipalityId municipalityId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.idDocumentTypeId = Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.biologicalSexId = Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        this.genderIdentityId = Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.mobilePhone = mobilePhone;
        this.homeAddress = homeAddress;
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.createdBy = createdBy;
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
        this.updatedBy = updatedBy;
        this.municipalityId = Objects.requireNonNull(municipalityId, "municipalityId must not be null");
    }

    public static Client register(
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            BiologicalSexId biologicalSexId,
            BiologicalSexId genderIdentityId,
            String email,
            String mobilePhone,
            String homeAddress,
            Boolean active,
            SpecialistId createdBy,
            SpecialistId updatedBy,
            MunicipalityId municipalityId) {
        ClientId id = ClientId.generate();
        LocalDateTime now = LocalDateTime.now();
        Client aggregate = new Client(
                id,
                idDocumentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                mobilePhone,
                homeAddress,
                active,
                now,
                createdBy,
                now,
                updatedBy,
                municipalityId);
        aggregate.recordEvent(new ClientRegisteredEvent(id, now));
        return aggregate;
    }

    public static Client restore(
            ClientId id,
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            BiologicalSexId biologicalSexId,
            BiologicalSexId genderIdentityId,
            String email,
            String mobilePhone,
            String homeAddress,
            Boolean active,
            LocalDateTime createdAt,
            SpecialistId createdBy,
            LocalDateTime updatedAt,
            SpecialistId updatedBy,
            MunicipalityId municipalityId) {
        return new Client(
                id,
                idDocumentTypeId,
                documentNumber,
                firstName,
                middleName,
                lastName,
                secondLastName,
                birthDate,
                biologicalSexId,
                genderIdentityId,
                email,
                mobilePhone,
                homeAddress,
                active,
                createdAt,
                createdBy,
                updatedAt,
                updatedBy,
                municipalityId);
    }

    public void update(
            IdDocumentTypeId idDocumentTypeId,
            String documentNumber,
            String firstName,
            String middleName,
            String lastName,
            String secondLastName,
            LocalDate birthDate,
            BiologicalSexId biologicalSexId,
            BiologicalSexId genderIdentityId,
            String email,
            String mobilePhone,
            String homeAddress,
            Boolean active,
            SpecialistId updatedBy,
            MunicipalityId municipalityId) {
        this.idDocumentTypeId = Objects.requireNonNull(idDocumentTypeId, "idDocumentTypeId must not be null");
        this.documentNumber = Objects.requireNonNull(documentNumber, "documentNumber must not be null");
        this.firstName = Objects.requireNonNull(firstName, "firstName must not be null");
        this.middleName = middleName;
        this.lastName = Objects.requireNonNull(lastName, "lastName must not be null");
        this.secondLastName = secondLastName;
        this.birthDate = Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.biologicalSexId = Objects.requireNonNull(biologicalSexId, "biologicalSexId must not be null");
        this.genderIdentityId = Objects.requireNonNull(genderIdentityId, "genderIdentityId must not be null");
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.mobilePhone = mobilePhone;
        this.homeAddress = homeAddress;
        this.active = Objects.requireNonNull(active, "active must not be null");
        this.updatedBy = updatedBy;
        this.municipalityId = Objects.requireNonNull(municipalityId, "municipalityId must not be null");
        LocalDateTime now = LocalDateTime.now();
        this.updatedAt = now;
        recordEvent(new ClientUpdatedEvent(
                this.id,
                this.idDocumentTypeId,
                this.documentNumber,
                this.firstName,
                this.middleName,
                this.lastName,
                this.secondLastName,
                this.birthDate,
                this.biologicalSexId,
                this.genderIdentityId,
                this.email,
                this.mobilePhone,
                this.homeAddress,
                this.active,
                this.updatedBy,
                this.municipalityId,
                now));
    }

    public ClientId id() {
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

    public String middleName() {
        return middleName;
    }

    public String lastName() {
        return lastName;
    }

    public String secondLastName() {
        return secondLastName;
    }

    public LocalDate birthDate() {
        return birthDate;
    }

    public BiologicalSexId biologicalSexId() {
        return biologicalSexId;
    }

    public BiologicalSexId genderIdentityId() {
        return genderIdentityId;
    }

    public String email() {
        return email;
    }

    public String mobilePhone() {
        return mobilePhone;
    }

    public String homeAddress() {
        return homeAddress;
    }

    public Boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public SpecialistId createdBy() {
        return createdBy;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }

    public SpecialistId updatedBy() {
        return updatedBy;
    }

    public MunicipalityId municipalityId() {
        return municipalityId;
    }
}
