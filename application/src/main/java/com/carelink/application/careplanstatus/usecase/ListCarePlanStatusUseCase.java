package com.carelink.application.careplanstatus.usecase;

import java.util.List;

import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.domain.careplanstatus.port.repository.CarePlanStatusRepository;

public class ListCarePlanStatusUseCase {
    private final CarePlanStatusRepository repository;

    public ListCarePlanStatusUseCase(CarePlanStatusRepository repository) {
        this.repository = repository;
    }

    public List<CarePlanStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CarePlanStatusResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
