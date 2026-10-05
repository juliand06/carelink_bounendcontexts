package com.carelink.application.biologicalsex.usecase;

import com.carelink.application.biologicalsex.command.RegisterBiologicalSexCommand;
import com.carelink.application.biologicalsex.dto.BiologicalSexResponse;
import com.carelink.domain.biologicalsex.port.repository.BiologicalSexRepository;
import com.carelink.domain.biologicalsex.model.aggregate.BiologicalSex;

public class RegisterBiologicalSexUseCase {
    private final BiologicalSexRepository repository;

    public RegisterBiologicalSexUseCase(BiologicalSexRepository repository) {
        this.repository = repository;
    }

    public BiologicalSexResponse execute(RegisterBiologicalSexCommand command) {
        BiologicalSex aggregate = BiologicalSex.register(
                command.label());
        BiologicalSex saved = repository.save(aggregate);
        return new BiologicalSexResponse(
                saved.id().value(),
                saved.label(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
