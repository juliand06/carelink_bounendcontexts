package com.carelink.domain.drugadministrationroute.port.repository;

import java.util.List;
import java.util.Optional;

import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public interface DrugAdministrationRouteRepository {
    DrugAdministrationRoute save(DrugAdministrationRoute aggregate);

    Optional<DrugAdministrationRoute> findById(DrugAdministrationRouteId id);

    List<DrugAdministrationRoute> findAll();

    void delete(DrugAdministrationRoute aggregate);
}
