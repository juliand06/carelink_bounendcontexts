package com.carelink.domain.careplanstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;

public interface CarePlanStatusRepository {
    CarePlanStatus save(CarePlanStatus aggregate);

    Optional<CarePlanStatus> findById(CarePlanStatusId id);

    List<CarePlanStatus> findAll();

    void delete(CarePlanStatus aggregate);
}
