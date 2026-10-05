package com.carelink.application.classificationsystem.usecase;

import com.carelink.application.classificationsystem.command.UpdateClassificationSystemCommand;
import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import com.carelink.application.classificationsystem.exception.ClassificationSystemNotFoundApplicationException;

public class UpdateClassificationSystemUseCase {
    private final ClassificationSystemRepository repository;

    public UpdateClassificationSystemUseCase(ClassificationSystemRepository repository) {
        this.repository = repository;
    }

    public ClassificationSystemResponse execute(UpdateClassificationSystemCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClassificationSystemNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.label(),
                command.active(),
                command.edition());
        var saved = repository.save(aggregate);
        return new ClassificationSystemResponse(
                saved.id().value(),
                saved.code(),
                saved.label(),
                saved.active(),
                saved.edition(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
