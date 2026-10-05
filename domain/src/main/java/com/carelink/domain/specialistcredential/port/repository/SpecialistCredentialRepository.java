package com.carelink.domain.specialistcredential.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.specialistcredential.model.aggregate.SpecialistCredential;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;

public interface SpecialistCredentialRepository {
    SpecialistCredential save(SpecialistCredential aggregate);

    Optional<SpecialistCredential> findById(SpecialistCredentialId id);

    List<SpecialistCredential> findAll();

    void delete(SpecialistCredential aggregate);
}
