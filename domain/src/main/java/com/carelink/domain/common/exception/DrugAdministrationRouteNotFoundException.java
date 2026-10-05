package com.carelink.domain.common.exception;

import com.carelink.domain.drugadministrationroute.model.valueobject.DrugAdministrationRouteId;

public class DrugAdministrationRouteNotFoundException extends RuntimeException {
    public DrugAdministrationRouteNotFoundException(DrugAdministrationRouteId id) {
        super("DrugAdministrationRoute not found with id: " + id.value());
    }
}
