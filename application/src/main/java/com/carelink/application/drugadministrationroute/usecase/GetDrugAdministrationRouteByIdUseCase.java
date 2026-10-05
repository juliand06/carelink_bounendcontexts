package com.carelink.application.drugadministrationroute.usecase;

import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import com.carelink.application.drugadministrationroute.exception.DrugAdministrationRouteNotFoundApplicationException;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public class GetDrugAdministrationRouteByIdUseCase {
    private final DrugAdministrationRouteRepository repository;

    public GetDrugAdministrationRouteByIdUseCase(DrugAdministrationRouteRepository repository) {
        this.repository = repository;
    }

    public DrugAdministrationRouteResponse execute(DrugAdministrationRouteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DrugAdministrationRouteNotFoundApplicationException(id.value().toString()));
        return new DrugAdministrationRouteResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.label(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
