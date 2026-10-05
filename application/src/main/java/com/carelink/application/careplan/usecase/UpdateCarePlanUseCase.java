package com.carelink.application.careplan.usecase;

import com.carelink.application.careplan.command.UpdateCarePlanCommand;
import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.application.careplan.exception.CarePlanNotFoundApplicationException;

public class UpdateCarePlanUseCase {
    private final CarePlanRepository repository;

    public UpdateCarePlanUseCase(CarePlanRepository repository) {
        this.repository = repository;
    }

    public CarePlanResponse execute(UpdateCarePlanCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CarePlanNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.appointmentId(),
                command.specialistId(),
                command.planTitle(),
                command.planDescription(),
                command.startDate(),
                command.projectedEndDate(),
                command.carePlanStatusId());
        var saved = repository.save(aggregate);
        return new CarePlanResponse(
                saved.id().value(),
                saved.appointmentId().value(),
                saved.specialistId().value(),
                saved.planTitle(),
                saved.planDescription(),
                saved.startDate(),
                saved.projectedEndDate(),
                saved.carePlanStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
