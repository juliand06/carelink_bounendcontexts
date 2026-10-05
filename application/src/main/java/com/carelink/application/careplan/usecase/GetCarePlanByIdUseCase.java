package com.carelink.application.careplan.usecase;

import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.domain.careplan.port.repository.CarePlanRepository;
import com.carelink.application.careplan.exception.CarePlanNotFoundApplicationException;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;

public class GetCarePlanByIdUseCase {
    private final CarePlanRepository repository;

    public GetCarePlanByIdUseCase(CarePlanRepository repository) {
        this.repository = repository;
    }

    public CarePlanResponse execute(CarePlanId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CarePlanNotFoundApplicationException(id.value().toString()));
        return new CarePlanResponse(
                aggregate.id().value(),
                aggregate.appointmentId().value(),
                aggregate.specialistId().value(),
                aggregate.planTitle(),
                aggregate.planDescription(),
                aggregate.startDate(),
                aggregate.projectedEndDate(),
                aggregate.carePlanStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
