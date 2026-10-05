package com.carelink.infrastructure.department.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.department.command.RegisterDepartmentCommand;
import com.carelink.application.department.command.UpdateDepartmentCommand;
import com.carelink.application.department.dto.DepartmentResponse;
import com.carelink.application.department.usecase.DeleteDepartmentUseCase;
import com.carelink.application.department.usecase.GetDepartmentByIdUseCase;
import com.carelink.application.department.usecase.ListDepartmentUseCase;
import com.carelink.application.department.usecase.RegisterDepartmentUseCase;
import com.carelink.application.department.usecase.UpdateDepartmentUseCase;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.department.model.valueobject.DepartmentId;
import com.carelink.infrastructure.department.adapters.in.rest.dtos.CreateDepartmentRequest;
import com.carelink.infrastructure.department.adapters.in.rest.dtos.UpdateDepartmentRequest;
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
@RequestMapping("/api/departments")
public class DepartmentController {
    private final RegisterDepartmentUseCase registerUseCase;
    private final GetDepartmentByIdUseCase getByIdUseCase;
    private final ListDepartmentUseCase listUseCase;
    private final UpdateDepartmentUseCase updateUseCase;
    private final DeleteDepartmentUseCase deleteUseCase;

    public DepartmentController(RegisterDepartmentUseCase registerUseCase,
            GetDepartmentByIdUseCase getByIdUseCase, ListDepartmentUseCase listUseCase,
            UpdateDepartmentUseCase updateUseCase, DeleteDepartmentUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponse> create(@Valid @RequestBody CreateDepartmentRequest request) {
        var command = new RegisterDepartmentCommand(
                request.name(),
                request.isoCode(),
                request.notes(),
                request.enabled(),
                new CountryId(request.countryId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DepartmentId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDepartmentRequest request) {
        var command = new UpdateDepartmentCommand(
                new DepartmentId(id),
                request.name(),
                request.isoCode(),
                request.notes(),
                request.enabled(),
                new CountryId(request.countryId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DepartmentId(id));
        return ResponseEntity.noContent().build();
    }
}
