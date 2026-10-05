package com.carelink.application.drugadministrationroute.usecase;

import java.time.LocalDateTime;

import com.carelink.application.drugadministrationroute.exception.DrugAdministrationRouteNotFoundApplicationException;
import com.carelink.domain.drugadministrationroute.event.DrugAdministrationRouteDeletedEvent;
import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;
import com.carelink.domain.drugadministrationroute.port.repository.DrugAdministrationRouteRepository;

public class DeleteDrugAdministrationRouteUseCase {
    private final DrugAdministrationRouteRepository repository;

    public DeleteDrugAdministrationRouteUseCase(DrugAdministrationRouteRepository repository) {
        this.repository = repository;
    }

    public DrugAdministrationRouteDeletedEvent execute(DrugAdministrationRouteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DrugAdministrationRouteNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DrugAdministrationRouteDeletedEvent(id, LocalDateTime.now());
    }
}
