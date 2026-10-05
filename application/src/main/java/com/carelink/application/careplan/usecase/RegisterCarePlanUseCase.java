package com.carelink.application.careplan.usecase;

import com.carelink.application.careplan.command.RegisterCarePlanCommand;
import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.domain.careplan.model.aggregate.CarePlan;

public class RegisterCarePlanUseCase {
    private final CarePlanRepository repository;

    public RegisterCarePlanUseCase(CarePlanRepository repository) {
        this.repository = repository;
    }

    public CarePlanResponse execute(RegisterCarePlanCommand command) {
        CarePlan aggregate = CarePlan.register(
                command.appointmentId(),
                command.specialistId(),
                command.planTitle(),
                command.planDescription(),
                command.startDate(),
                command.projectedEndDate(),
                command.carePlanStatusId());
        CarePlan saved = repository.save(aggregate);
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
