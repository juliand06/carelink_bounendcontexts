package com.carelink.application.drugadministrationroute.exception;

import com.carelink.application.common.exception.NotFoundApplicationException;

public class DrugAdministrationRouteNotFoundApplicationException extends NotFoundApplicationException {
    public DrugAdministrationRouteNotFoundApplicationException(String message) {
        super(message);
    }
}
