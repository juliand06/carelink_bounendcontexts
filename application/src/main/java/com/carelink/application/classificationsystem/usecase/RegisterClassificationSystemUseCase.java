package com.carelink.application.classificationsystem.usecase;

import com.carelink.application.classificationsystem.command.RegisterClassificationSystemCommand;
import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.domain.classificationsystem.port.repository.ClassificationSystemRepository;
import com.carelink.domain.classificationsystem.model.aggregate.ClassificationSystem;

public class RegisterClassificationSystemUseCase {
    private final ClassificationSystemRepository repository;

    public RegisterClassificationSystemUseCase(ClassificationSystemRepository repository) {
        this.repository = repository;
    }

    public ClassificationSystemResponse execute(RegisterClassificationSystemCommand command) {
        ClassificationSystem aggregate = ClassificationSystem.register(
                command.code(),
                command.label(),
                command.active(),
                command.edition());
        ClassificationSystem saved = repository.save(aggregate);
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
