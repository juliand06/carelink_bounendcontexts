package com.carelink.application.careplanstatus.usecase;

import com.carelink.application.careplanstatus.command.RegisterCarePlanStatusCommand;
import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;
import com.carelink.domain.careplanstatus.model.aggregate.CarePlanStatus;

public class RegisterCarePlanStatusUseCase {
    private final CarePlanStatusRepository repository;

    public RegisterCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        this.repository = repository;
    }

    public CarePlanStatusResponse execute(RegisterCarePlanStatusCommand command) {
        CarePlanStatus aggregate = CarePlanStatus.register(
                command.code(),
                command.label(),
                command.active());
        CarePlanStatus saved = repository.save(aggregate);
        return new CarePlanStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
