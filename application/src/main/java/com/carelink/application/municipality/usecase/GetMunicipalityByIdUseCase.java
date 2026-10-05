package com.carelink.application.municipality.usecase;

import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.domain.municipality.port.repository.MunicipalityRepository;
import com.carelink.application.municipality.exception.MunicipalityNotFoundApplicationException;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;

public class GetMunicipalityByIdUseCase {
    private final MunicipalityRepository repository;

    public GetMunicipalityByIdUseCase(MunicipalityRepository repository) {
        this.repository = repository;
    }

    public MunicipalityResponse execute(MunicipalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MunicipalityNotFoundApplicationException(id.value().toString()));
        return new MunicipalityResponse(
                aggregate.id().value(),
                aggregate.name(),
                aggregate.isoCode(),
                aggregate.notes(),
                aggregate.enabled(),
                aggregate.departmentId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
