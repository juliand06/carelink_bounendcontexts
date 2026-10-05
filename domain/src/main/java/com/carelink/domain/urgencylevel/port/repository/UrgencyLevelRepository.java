package com.carelink.domain.urgencylevel.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.urgencylevel.model.aggregate.UrgencyLevel;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;

public interface UrgencyLevelRepository {
    UrgencyLevel save(UrgencyLevel aggregate);

    Optional<UrgencyLevel> findById(UrgencyLevelId id);

    List<UrgencyLevel> findAll();

    void delete(UrgencyLevel aggregate);
}
