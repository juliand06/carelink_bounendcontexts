package com.carelink.application.dangerlevel.usecase;

import java.util.List;

import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.domain.dangerlevel.port.repository.DangerLevelRepository;

public class ListDangerLevelUseCase {
    private final DangerLevelRepository repository;

    public ListDangerLevelUseCase(DangerLevelRepository repository) {
        this.repository = repository;
    }

    public List<DangerLevelResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new DangerLevelResponse(
                        aggregate.id().value(),
                        aggregate.code(),
                        aggregate.label(),
                        aggregate.active(),
                        aggregate.severityRank(),
                        aggregate.createdAt(),
                        aggregate.updatedAt()))
                .toList();
    }
}
