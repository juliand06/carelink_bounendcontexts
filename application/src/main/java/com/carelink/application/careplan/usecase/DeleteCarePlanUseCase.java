package com.carelink.application.careplan.usecase;

import java.time.LocalDateTime;

import com.carelink.application.careplan.exception.CarePlanNotFoundApplicationException;
import com.carelink.domain.careplan.event.CarePlanDeletedEvent;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;

public class DeleteCarePlanUseCase {
    private final CarePlanRepository repository;

    public DeleteCarePlanUseCase(CarePlanRepository repository) {
        this.repository = repository;
    }

    public CarePlanDeletedEvent execute(CarePlanId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CarePlanNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CarePlanDeletedEvent(id, LocalDateTime.now());
    }
}
