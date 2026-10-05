package com.carelink.domain.handoffstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffstatus.model.aggregate.HandoffStatus;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;

public interface HandoffStatusRepository {
    HandoffStatus save(HandoffStatus aggregate);

    Optional<HandoffStatus> findById(HandoffStatusId id);

    List<HandoffStatus> findAll();

    void delete(HandoffStatus aggregate);
}
