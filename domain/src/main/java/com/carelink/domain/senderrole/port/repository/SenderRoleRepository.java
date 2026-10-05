package com.carelink.domain.senderrole.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.senderrole.model.aggregate.SenderRole;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;

public interface SenderRoleRepository {
    SenderRole save(SenderRole aggregate);

    Optional<SenderRole> findById(SenderRoleId id);

    List<SenderRole> findAll();

    void delete(SenderRole aggregate);
}
