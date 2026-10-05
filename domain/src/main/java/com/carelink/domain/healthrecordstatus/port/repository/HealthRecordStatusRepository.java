package com.carelink.domain.healthrecordstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.healthrecordstatus.model.aggregate.HealthRecordStatus;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;

public interface HealthRecordStatusRepository {
    HealthRecordStatus save(HealthRecordStatus aggregate);

    Optional<HealthRecordStatus> findById(HealthRecordStatusId id);

    List<HealthRecordStatus> findAll();

    void delete(HealthRecordStatus aggregate);
}
