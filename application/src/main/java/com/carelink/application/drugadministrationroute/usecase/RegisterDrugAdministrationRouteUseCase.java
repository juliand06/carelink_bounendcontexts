package com.carelink.application.drugadministrationroute.usecase;

import com.carelink.application.drugadministrationroute.command.RegisterDrugAdministrationRouteCommand;
import com.carelink.application.drugadministrationroute.dto.DrugAdministrationRouteResponse;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;
import com.carelink.domain.drugadministrationroute.model.aggregate.DrugAdministrationRoute;

public class RegisterDrugAdministrationRouteUseCase {
    private final DrugAdministrationRouteRepository repository;

    public RegisterDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        this.repository = repository;
    }

    public DrugAdministrationRouteResponse execute(RegisterDrugAdministrationRouteCommand command) {
        DrugAdministrationRoute aggregate = DrugAdministrationRoute.register(
                command.code(),
                command.label(),
                command.active());
        DrugAdministrationRoute saved = repository.save(aggregate);
        return new DrugAdministrationRouteResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
