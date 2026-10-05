package com.carelink.infrastructure.handoffstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.handoffstatus.command.RegisterHandoffStatusCommand;
import com.carelink.application.handoffstatus.command.UpdateHandoffStatusCommand;
import com.carelink.application.handoffstatus.dto.HandoffStatusResponse;
import com.carelink.application.handoffstatus.usecase.DeleteHandoffStatusUseCase;
import com.carelink.application.handoffstatus.usecase.GetHandoffStatusByIdUseCase;
import com.carelink.application.handoffstatus.usecase.ListHandoffStatusUseCase;
import com.carelink.application.handoffstatus.usecase.RegisterHandoffStatusUseCase;
import com.carelink.application.handoffstatus.usecase.UpdateHandoffStatusUseCase;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.infrastructure.handoffstatus.adapters.in.rest.dtos.CreateHandoffStatusRequest;
import com.carelink.infrastructure.handoffstatus.adapters.in.rest.dtos.UpdateHandoffStatusRequest;
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
@RequestMapping("/api/handoff-statuses")
public class HandoffStatusController {
    private final RegisterHandoffStatusUseCase registerUseCase;
    private final GetHandoffStatusByIdUseCase getByIdUseCase;
    private final ListHandoffStatusUseCase listUseCase;
    private final UpdateHandoffStatusUseCase updateUseCase;
    private final DeleteHandoffStatusUseCase deleteUseCase;

    public HandoffStatusController(RegisterHandoffStatusUseCase registerUseCase,
            GetHandoffStatusByIdUseCase getByIdUseCase, ListHandoffStatusUseCase listUseCase,
            UpdateHandoffStatusUseCase updateUseCase, DeleteHandoffStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HandoffStatusResponse> create(@Valid @RequestBody CreateHandoffStatusRequest request) {
        var command = new RegisterHandoffStatusCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HandoffStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandoffStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HandoffStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HandoffStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHandoffStatusRequest request) {
        var command = new UpdateHandoffStatusCommand(
                new HandoffStatusId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HandoffStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
