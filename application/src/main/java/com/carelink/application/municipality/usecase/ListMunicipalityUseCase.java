package com.carelink.application.municipality.usecase;

import java.util.List;

import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;

public class ListMunicipalityUseCase {
    private final MunicipalityRepository repository;

    public ListMunicipalityUseCase(MunicipalityRepository repository) {
        this.repository = repository;
    }

    public List<MunicipalityResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new MunicipalityResponse(
                        aggregate.id().value(),
                        aggregate.name(),
                        aggregate.isoCode(),
                        aggregate.notes(),
                        aggregate.enabled(),
                        aggregate.departmentId().value(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
