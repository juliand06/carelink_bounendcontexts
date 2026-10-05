package com.carelink.infrastructure.classificationsystem.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.classificationsystem.command.RegisterClassificationSystemCommand;
import com.carelink.application.classificationsystem.command.UpdateClassificationSystemCommand;
import com.carelink.application.classificationsystem.dto.ClassificationSystemResponse;
import com.carelink.application.classificationsystem.usecase.DeleteClassificationSystemUseCase;
import com.carelink.application.classificationsystem.usecase.GetClassificationSystemByIdUseCase;
import com.carelink.application.classificationsystem.usecase.ListClassificationSystemUseCase;
import com.carelink.application.classificationsystem.usecase.RegisterClassificationSystemUseCase;
import com.carelink.application.classificationsystem.usecase.UpdateClassificationSystemUseCase;
import com.carelink.domain.classificationsystem.model.valueobject.ClassificationSystemId;
import com.carelink.infrastructure.classificationsystem.adapters.in.rest.dtos.CreateClassificationSystemRequest;
import com.carelink.infrastructure.classificationsystem.adapters.in.rest.dtos.UpdateClassificationSystemRequest;
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
@RequestMapping("/api/classification-systems")
public class ClassificationSystemController {
    private final RegisterClassificationSystemUseCase registerUseCase;
    private final GetClassificationSystemByIdUseCase getByIdUseCase;
    private final ListClassificationSystemUseCase listUseCase;
    private final UpdateClassificationSystemUseCase updateUseCase;
    private final DeleteClassificationSystemUseCase deleteUseCase;

    public ClassificationSystemController(RegisterClassificationSystemUseCase registerUseCase,
            GetClassificationSystemByIdUseCase getByIdUseCase, ListClassificationSystemUseCase listUseCase,
            UpdateClassificationSystemUseCase updateUseCase, DeleteClassificationSystemUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClassificationSystemResponse> create(@Valid @RequestBody CreateClassificationSystemRequest request) {
        var command = new RegisterClassificationSystemCommand(
                request.code(),
                request.label(),
                request.active(),
                request.edition());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClassificationSystemResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassificationSystemResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClassificationSystemId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassificationSystemResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateClassificationSystemRequest request) {
        var command = new UpdateClassificationSystemCommand(
                new ClassificationSystemId(id),
                request.code(),
                request.label(),
                request.active(),
                request.edition());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ClassificationSystemId(id));
        return ResponseEntity.noContent().build();
    }
}
