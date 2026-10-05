package com.carelink.domain.handoffstatusaudit.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.handoffstatusaudit.model.aggregate.HandoffStatusAudit;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;

public interface HandoffStatusAuditRepository {
    HandoffStatusAudit save(HandoffStatusAudit aggregate);

    Optional<HandoffStatusAudit> findById(HandoffStatusAuditId id);

    List<HandoffStatusAudit> findAll();

    void delete(HandoffStatusAudit aggregate);
}
