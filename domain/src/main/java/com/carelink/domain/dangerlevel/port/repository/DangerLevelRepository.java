package com.carelink.domain.dangerlevel.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.dangerlevel.model.aggregate.DangerLevel;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;

public interface DangerLevelRepository {
    DangerLevel save(DangerLevel aggregate);

    Optional<DangerLevel> findById(DangerLevelId id);

    List<DangerLevel> findAll();

    void delete(DangerLevel aggregate);
}
