package com.carelink.domain.client.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.client.model.aggregate.Client;
import com.carelink.domain.client.model.valueobject.ClientId;

public interface ClientRepository {
    Client save(Client aggregate);

    Optional<Client> findById(ClientId id);

    List<Client> findAll();

    void delete(Client aggregate);
}
