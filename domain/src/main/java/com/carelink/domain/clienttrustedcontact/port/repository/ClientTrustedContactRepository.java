package com.carelink.domain.clienttrustedcontact.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.clienttrustedcontact.model.aggregate.ClientTrustedContact;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;

public interface ClientTrustedContactRepository {
    ClientTrustedContact save(ClientTrustedContact aggregate);

    Optional<ClientTrustedContact> findById(ClientTrustedContactId id);

    List<ClientTrustedContact> findAll();

    void delete(ClientTrustedContact aggregate);
}
