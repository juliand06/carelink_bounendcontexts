package com.carelink.application.drugadministrationroute.usecase;

import java.util.List;

import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;

public class ListDrugAdministrationRouteUseCase {
    private final DrugAdministrationRouteRepository repository;

    public ListDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        this.repository = repository;
    }

    public List<DrugAdministrationRouteResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new DrugAdministrationRouteResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
