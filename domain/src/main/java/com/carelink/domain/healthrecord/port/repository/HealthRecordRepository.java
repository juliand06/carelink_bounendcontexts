package com.carelink.domain.healthrecord.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.healthrecord.model.aggregate.HealthRecord;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;

public interface HealthRecordRepository {
    HealthRecord save(HealthRecord aggregate);

    Optional<HealthRecord> findById(HealthRecordId id);

    List<HealthRecord> findAll();

    void delete(HealthRecord aggregate);
}
