package com.carelink.application.drugadministrationroute.usecase;

import com.carelink.application.drugadministrationroute.command.UpdateDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import com.carelink.application.drugadministrationroute.exception.DrugAdministrationRouteNotFoundApplicationException;

public class UpdateDrugAdministrationRouteUseCase {
    private final DrugAdministrationRouteRepository repository;

    public UpdateDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        this.repository = repository;
    }

    public DrugAdministrationRouteResponse execute(UpdateDrugAdministrationRouteCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DrugAdministrationRouteNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new DrugAdministrationRouteResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
