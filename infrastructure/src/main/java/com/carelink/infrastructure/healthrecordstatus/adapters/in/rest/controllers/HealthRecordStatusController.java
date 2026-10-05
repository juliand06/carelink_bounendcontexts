package com.carelink.infrastructure.healthrecordstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.healthrecordstatus.command.RegisterHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.command.UpdateHealthRecordStatusCommand;
import com.carelink.application.healthrecordstatus.dto.HealthRecordStatusResponse;
import com.carelink.application.healthrecordstatus.usecase.DeleteHealthRecordStatusUseCase;
import com.carelink.application.healthrecordstatus.usecase.GetHealthRecordStatusByIdUseCase;
import com.carelink.application.healthrecordstatus.usecase.ListHealthRecordStatusUseCase;
import com.carelink.application.healthrecordstatus.usecase.RegisterHealthRecordStatusUseCase;
import com.carelink.application.healthrecordstatus.usecase.UpdateHealthRecordStatusUseCase;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.infrastructure.healthrecordstatus.adapters.in.rest.dtos.CreateHealthRecordStatusRequest;
import com.carelink.infrastructure.healthrecordstatus.adapters.in.rest.dtos.UpdateHealthRecordStatusRequest;
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
@RequestMapping("/api/health-record-statuses")
public class HealthRecordStatusController {
    private final RegisterHealthRecordStatusUseCase registerUseCase;
    private final GetHealthRecordStatusByIdUseCase getByIdUseCase;
    private final ListHealthRecordStatusUseCase listUseCase;
    private final UpdateHealthRecordStatusUseCase updateUseCase;
    private final DeleteHealthRecordStatusUseCase deleteUseCase;

    public HealthRecordStatusController(RegisterHealthRecordStatusUseCase registerUseCase,
            GetHealthRecordStatusByIdUseCase getByIdUseCase, ListHealthRecordStatusUseCase listUseCase,
            UpdateHealthRecordStatusUseCase updateUseCase, DeleteHealthRecordStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HealthRecordStatusResponse> create(@Valid @RequestBody CreateHealthRecordStatusRequest request) {
        var command = new RegisterHealthRecordStatusCommand(
                request.code(),
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HealthRecordStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthRecordStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HealthRecordStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHealthRecordStatusRequest request) {
        var command = new UpdateHealthRecordStatusCommand(
                new HealthRecordStatusId(id),
                request.code(),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HealthRecordStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
