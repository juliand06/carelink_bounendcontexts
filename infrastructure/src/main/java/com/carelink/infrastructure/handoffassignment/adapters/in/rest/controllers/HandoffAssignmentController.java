package com.carelink.infrastructure.handoffassignment.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.handoffassignment.command.RegisterHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.command.UpdateHandoffAssignmentCommand;
import com.carelink.application.handoffassignment.dto.HandoffAssignmentResponse;
import com.carelink.application.handoffassignment.usecase.DeleteHandoffAssignmentUseCase;
import com.carelink.application.handoffassignment.usecase.GetHandoffAssignmentByIdUseCase;
import com.carelink.application.handoffassignment.usecase.ListHandoffAssignmentUseCase;
import com.carelink.application.handoffassignment.usecase.RegisterHandoffAssignmentUseCase;
import com.carelink.application.handoffassignment.usecase.UpdateHandoffAssignmentUseCase;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffassignment.model.valueobject.HandoffAssignmentId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.handoffassignment.adapters.in.rest.dtos.CreateHandoffAssignmentRequest;
import com.carelink.infrastructure.handoffassignment.adapters.in.rest.dtos.UpdateHandoffAssignmentRequest;
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
@RequestMapping("/api/handoff-assignments")
public class HandoffAssignmentController {
    private final RegisterHandoffAssignmentUseCase registerUseCase;
    private final GetHandoffAssignmentByIdUseCase getByIdUseCase;
    private final ListHandoffAssignmentUseCase listUseCase;
    private final UpdateHandoffAssignmentUseCase updateUseCase;
    private final DeleteHandoffAssignmentUseCase deleteUseCase;

    public HandoffAssignmentController(RegisterHandoffAssignmentUseCase registerUseCase,
            GetHandoffAssignmentByIdUseCase getByIdUseCase, ListHandoffAssignmentUseCase listUseCase,
            UpdateHandoffAssignmentUseCase updateUseCase, DeleteHandoffAssignmentUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HandoffAssignmentResponse> create(@Valid @RequestBody CreateHandoffAssignmentRequest request) {
        var command = new RegisterHandoffAssignmentCommand(
                new HandoffId(request.handoffId()),
                new SpecialistId(request.specialistId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HandoffAssignmentResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandoffAssignmentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HandoffAssignmentId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HandoffAssignmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHandoffAssignmentRequest request) {
        var command = new UpdateHandoffAssignmentCommand(
                new HandoffAssignmentId(id),
                new HandoffId(request.handoffId()),
                new SpecialistId(request.specialistId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HandoffAssignmentId(id));
        return ResponseEntity.noContent().build();
    }
}
