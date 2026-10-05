package com.carelink.infrastructure.caregoalstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.caregoalstatus.command.RegisterCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.command.UpdateCareGoalStatusCommand;
import com.carelink.application.caregoalstatus.dto.CareGoalStatusResponse;
import com.carelink.application.caregoalstatus.usecase.DeleteCareGoalStatusUseCase;
import com.carelink.application.caregoalstatus.usecase.GetCareGoalStatusByIdUseCase;
import com.carelink.application.caregoalstatus.usecase.ListCareGoalStatusUseCase;
import com.carelink.application.caregoalstatus.usecase.RegisterCareGoalStatusUseCase;
import com.carelink.application.caregoalstatus.usecase.UpdateCareGoalStatusUseCase;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.infrastructure.caregoalstatus.adapters.in.rest.dtos.CreateCareGoalStatusRequest;
import com.carelink.infrastructure.caregoalstatus.adapters.in.rest.dtos.UpdateCareGoalStatusRequest;
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
@RequestMapping("/api/care-goal-statuses")
public class CareGoalStatusController {
    private final RegisterCareGoalStatusUseCase registerUseCase;
    private final GetCareGoalStatusByIdUseCase getByIdUseCase;
    private final ListCareGoalStatusUseCase listUseCase;
    private final UpdateCareGoalStatusUseCase updateUseCase;
    private final DeleteCareGoalStatusUseCase deleteUseCase;

    public CareGoalStatusController(RegisterCareGoalStatusUseCase registerUseCase,
            GetCareGoalStatusByIdUseCase getByIdUseCase, ListCareGoalStatusUseCase listUseCase,
            UpdateCareGoalStatusUseCase updateUseCase, DeleteCareGoalStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CareGoalStatusResponse> create(@Valid @RequestBody CreateCareGoalStatusRequest request) {
        var command = new RegisterCareGoalStatusCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CareGoalStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareGoalStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CareGoalStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareGoalStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCareGoalStatusRequest request) {
        var command = new UpdateCareGoalStatusCommand(
                new CareGoalStatusId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new CareGoalStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
