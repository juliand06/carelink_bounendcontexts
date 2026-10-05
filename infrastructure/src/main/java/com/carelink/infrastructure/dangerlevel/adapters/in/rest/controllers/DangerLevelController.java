package com.carelink.infrastructure.dangerlevel.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.dangerlevel.command.RegisterDangerLevelCommand;
import com.carelink.application.dangerlevel.command.UpdateDangerLevelCommand;
import com.carelink.application.dangerlevel.dto.DangerLevelResponse;
import com.carelink.application.dangerlevel.usecase.DeleteDangerLevelUseCase;
import com.carelink.application.dangerlevel.usecase.GetDangerLevelByIdUseCase;
import com.carelink.application.dangerlevel.usecase.ListDangerLevelUseCase;
import com.carelink.application.dangerlevel.usecase.RegisterDangerLevelUseCase;
import com.carelink.application.dangerlevel.usecase.UpdateDangerLevelUseCase;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.infrastructure.dangerlevel.adapters.in.rest.dtos.CreateDangerLevelRequest;
import com.carelink.infrastructure.dangerlevel.adapters.in.rest.dtos.UpdateDangerLevelRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/danger-levels")
public class DangerLevelController {
    private final RegisterDangerLevelUseCase registerUseCase;
    private final GetDangerLevelByIdUseCase getByIdUseCase;
    private final ListDangerLevelUseCase listUseCase;
    private final UpdateDangerLevelUseCase updateUseCase;
    private final DeleteDangerLevelUseCase deleteUseCase;

    public DangerLevelController(RegisterDangerLevelUseCase registerUseCase,
            GetDangerLevelByIdUseCase getByIdUseCase, ListDangerLevelUseCase listUseCase,
            UpdateDangerLevelUseCase updateUseCase, DeleteDangerLevelUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DangerLevelResponse> create(@Valid @RequestBody CreateDangerLevelRequest request) {
        var command = new RegisterDangerLevelCommand(
                request.code(),
                request.label(),
                request.active(),
                request.severityRank());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DangerLevelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DangerLevelResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DangerLevelId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DangerLevelResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDangerLevelRequest request) {
        var command = new UpdateDangerLevelCommand(
                new DangerLevelId(id),
                request.code(),
                request.label(),
                request.active(),
                request.severityRank());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DangerLevelId(id));
        return ResponseEntity.noContent().build();
    }
}
