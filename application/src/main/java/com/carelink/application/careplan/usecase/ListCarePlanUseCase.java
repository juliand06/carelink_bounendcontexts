package com.carelink.application.careplan.usecase;

import java.util.List;

import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;

public class ListCarePlanUseCase {
    private final CarePlanRepository repository;

    public ListCarePlanUseCase(CarePlanRepository repository) {
        this.repository = repository;
    }

    public List<CarePlanResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CarePlanResponse(
                        aggregate.id().value(),
                        aggregate.appointmentId().value(),
                        aggregate.specialistId().value(),
                        aggregate.planTitle(),
                        aggregate.planDescription(),
                        aggregate.startDate(),
                        aggregate.projectedEndDate(),
                        aggregate.carePlanStatusId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
