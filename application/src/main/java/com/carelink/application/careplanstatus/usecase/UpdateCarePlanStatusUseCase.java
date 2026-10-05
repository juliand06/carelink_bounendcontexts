package com.carelink.application.careplanstatus.usecase;

import com.carelink.application.careplanstatus.command.UpdateCarePlanStatusCommand;
import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import com.carelink.application.careplanstatus.exception.CarePlanStatusNotFoundApplicationException;

public class UpdateCarePlanStatusUseCase {
    private final CarePlanStatusRepository repository;

    public UpdateCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        this.repository = repository;
    }

    public CarePlanStatusResponse execute(UpdateCarePlanStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CarePlanStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active());
        var saved = repository.save(aggregate);
        return new CarePlanStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
