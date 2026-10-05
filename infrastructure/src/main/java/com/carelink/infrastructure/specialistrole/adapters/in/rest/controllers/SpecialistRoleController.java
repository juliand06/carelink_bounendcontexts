package com.carelink.infrastructure.specialistrole.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.specialistrole.command.RegisterSpecialistRoleCommand;
import com.carelink.application.specialistrole.command.UpdateSpecialistRoleCommand;
import com.carelink.application.specialistrole.dto.SpecialistRoleResponse;
import com.carelink.application.specialistrole.usecase.DeleteSpecialistRoleUseCase;
import com.carelink.application.specialistrole.usecase.GetSpecialistRoleByIdUseCase;
import com.carelink.application.specialistrole.usecase.ListSpecialistRoleUseCase;
import com.carelink.application.specialistrole.usecase.RegisterSpecialistRoleUseCase;
import com.carelink.application.specialistrole.usecase.UpdateSpecialistRoleUseCase;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.infrastructure.specialistrole.adapters.in.rest.dtos.CreateSpecialistRoleRequest;
import com.carelink.infrastructure.specialistrole.adapters.in.rest.dtos.UpdateSpecialistRoleRequest;
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
@RequestMapping("/api/specialist-roles")
public class SpecialistRoleController {
    private final RegisterSpecialistRoleUseCase registerUseCase;
    private final GetSpecialistRoleByIdUseCase getByIdUseCase;
    private final ListSpecialistRoleUseCase listUseCase;
    private final UpdateSpecialistRoleUseCase updateUseCase;
    private final DeleteSpecialistRoleUseCase deleteUseCase;

    public SpecialistRoleController(RegisterSpecialistRoleUseCase registerUseCase,
            GetSpecialistRoleByIdUseCase getByIdUseCase, ListSpecialistRoleUseCase listUseCase,
            UpdateSpecialistRoleUseCase updateUseCase, DeleteSpecialistRoleUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SpecialistRoleResponse> create(@Valid @RequestBody CreateSpecialistRoleRequest request) {
        var command = new RegisterSpecialistRoleCommand(
                request.title());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SpecialistRoleResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialistRoleResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SpecialistRoleId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialistRoleResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSpecialistRoleRequest request) {
        var command = new UpdateSpecialistRoleCommand(
                new SpecialistRoleId(id),
                request.title());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SpecialistRoleId(id));
        return ResponseEntity.noContent().build();
    }
}
