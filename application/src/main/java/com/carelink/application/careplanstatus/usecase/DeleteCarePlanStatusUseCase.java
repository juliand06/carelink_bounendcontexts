package com.carelink.application.careplanstatus.usecase;

import java.time.LocalDateTime;

import com.carelink.application.careplanstatus.exception.CarePlanStatusNotFoundApplicationException;
import com.carelink.domain.careplanstatus.event.CarePlanStatusDeletedEvent;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;

public class DeleteCarePlanStatusUseCase {
    private final CarePlanStatusRepository repository;

    public DeleteCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        this.repository = repository;
    }

    public CarePlanStatusDeletedEvent execute(CarePlanStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CarePlanStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CarePlanStatusDeletedEvent(id, LocalDateTime.now());
    }
}
