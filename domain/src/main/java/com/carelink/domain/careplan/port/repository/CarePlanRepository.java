package com.carelink.domain.careplan.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.careplan.model.aggregate.CarePlan;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;

public interface CarePlanRepository {
    CarePlan save(CarePlan aggregate);

    Optional<CarePlan> findById(CarePlanId id);

    List<CarePlan> findAll();

    void delete(CarePlan aggregate);
}
