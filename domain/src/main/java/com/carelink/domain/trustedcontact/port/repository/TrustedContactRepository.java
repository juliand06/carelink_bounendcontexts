package com.carelink.domain.trustedcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.trustedcontact.model.aggregate.TrustedContact;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;

public interface TrustedContactRepository {
    TrustedContact save(TrustedContact aggregate);

    Optional<TrustedContact> findById(TrustedContactId id);

    List<TrustedContact> findAll();

    void delete(TrustedContact aggregate);
}
