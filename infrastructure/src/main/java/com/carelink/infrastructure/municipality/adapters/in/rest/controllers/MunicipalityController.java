package com.carelink.infrastructure.municipality.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.municipality.command.RegisterMunicipalityCommand;
import com.carelink.application.municipality.command.UpdateMunicipalityCommand;
import com.carelink.application.municipality.dto.MunicipalityResponse;
import com.carelink.application.municipality.usecase.DeleteMunicipalityUseCase;
import com.carelink.application.municipality.usecase.GetMunicipalityByIdUseCase;
import com.carelink.application.municipality.usecase.ListMunicipalityUseCase;
import com.carelink.application.municipality.usecase.RegisterMunicipalityUseCase;
import com.carelink.application.municipality.usecase.UpdateMunicipalityUseCase;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.infrastructure.municipality.adapters.in.rest.dtos.CreateMunicipalityRequest;
import com.carelink.infrastructure.municipality.adapters.in.rest.dtos.UpdateMunicipalityRequest;
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
@RequestMapping("/api/municipalities")
public class MunicipalityController {
    private final RegisterMunicipalityUseCase registerUseCase;
    private final GetMunicipalityByIdUseCase getByIdUseCase;
    private final ListMunicipalityUseCase listUseCase;
    private final UpdateMunicipalityUseCase updateUseCase;
    private final DeleteMunicipalityUseCase deleteUseCase;

    public MunicipalityController(RegisterMunicipalityUseCase registerUseCase,
            GetMunicipalityByIdUseCase getByIdUseCase, ListMunicipalityUseCase listUseCase,
            UpdateMunicipalityUseCase updateUseCase, DeleteMunicipalityUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MunicipalityResponse> create(@Valid @RequestBody CreateMunicipalityRequest request) {
        var command = new RegisterMunicipalityCommand(
                request.name(),
                request.isoCode(),
                request.notes(),
                request.enabled(),
                new DepartmentId(request.departmentId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MunicipalityResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipalityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MunicipalityId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipalityResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMunicipalityRequest request) {
        var command = new UpdateMunicipalityCommand(
                new MunicipalityId(id),
                request.name(),
                request.isoCode(),
                request.notes(),
                request.enabled(),
                new DepartmentId(request.departmentId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new MunicipalityId(id));
        return ResponseEntity.noContent().build();
    }
}
