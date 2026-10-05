package com.carelink.application.biologicalsex.usecase;

import com.carelink.application.biologicalsex.command.UpdateBiologicalSexCommand;
import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import com.carelink.application.biologicalsex.exception.BiologicalSexNotFoundApplicationException;

public class UpdateBiologicalSexUseCase {
    private final BiologicalSexRepository repository;

    public UpdateBiologicalSexUseCase(BiologicalSexRepository repository) {
        this.repository = repository;
    }

    public BiologicalSexResponse execute(UpdateBiologicalSexCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new BiologicalSexNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.label());
        var saved = repository.save(aggregate);
        return new BiologicalSexResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
