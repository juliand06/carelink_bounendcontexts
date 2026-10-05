package com.carelink.infrastructure.healthrecord.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.healthrecord.command.RegisterHealthRecordCommand;
import com.carelink.application.healthrecord.command.UpdateHealthRecordCommand;
import com.carelink.application.healthrecord.dto.HealthRecordResponse;
import com.carelink.application.healthrecord.usecase.DeleteHealthRecordUseCase;
import com.carelink.application.healthrecord.usecase.GetHealthRecordByIdUseCase;
import com.carelink.application.healthrecord.usecase.ListHealthRecordUseCase;
import com.carelink.application.healthrecord.usecase.RegisterHealthRecordUseCase;
import com.carelink.application.healthrecord.usecase.UpdateHealthRecordUseCase;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.healthrecordstatus.model.valueobject.HealthRecordStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.healthrecord.adapters.in.rest.dtos.CreateHealthRecordRequest;
import com.carelink.infrastructure.healthrecord.adapters.in.rest.dtos.UpdateHealthRecordRequest;
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
@RequestMapping("/api/health-records")
public class HealthRecordController {
    private final RegisterHealthRecordUseCase registerUseCase;
    private final GetHealthRecordByIdUseCase getByIdUseCase;
    private final ListHealthRecordUseCase listUseCase;
    private final UpdateHealthRecordUseCase updateUseCase;
    private final DeleteHealthRecordUseCase deleteUseCase;

    public HealthRecordController(RegisterHealthRecordUseCase registerUseCase,
            GetHealthRecordByIdUseCase getByIdUseCase, ListHealthRecordUseCase listUseCase,
            UpdateHealthRecordUseCase updateUseCase, DeleteHealthRecordUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HealthRecordResponse> create(@Valid @RequestBody CreateHealthRecordRequest request) {
        var command = new RegisterHealthRecordCommand(
                new ClientId(request.clientId()),
                request.recordCode(),
                request.openedAt(),
                request.closedAt(),
                new HealthRecordStatusId(request.statusId()),
                new SpecialistId(request.createdBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HealthRecordResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HealthRecordResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HealthRecordId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HealthRecordResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHealthRecordRequest request) {
        var command = new UpdateHealthRecordCommand(
                new HealthRecordId(id),
                new ClientId(request.clientId()),
                request.recordCode(),
                request.openedAt(),
                request.closedAt(),
                new HealthRecordStatusId(request.statusId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HealthRecordId(id));
        return ResponseEntity.noContent().build();
    }
}
