package com.carelink.infrastructure.careplanstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.careplanstatus.command.RegisterCarePlanStatusCommand;
import com.carelink.application.careplanstatus.command.UpdateCarePlanStatusCommand;
import com.carelink.application.careplanstatus.dto.CarePlanStatusResponse;
import com.carelink.application.careplanstatus.usecase.DeleteCarePlanStatusUseCase;
import com.carelink.application.careplanstatus.usecase.GetCarePlanStatusByIdUseCase;
import com.carelink.application.careplanstatus.usecase.ListCarePlanStatusUseCase;
import com.carelink.application.careplanstatus.usecase.RegisterCarePlanStatusUseCase;
import com.carelink.application.careplanstatus.usecase.UpdateCarePlanStatusUseCase;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.infrastructure.careplanstatus.adapters.in.rest.dtos.CreateCarePlanStatusRequest;
import com.carelink.infrastructure.careplanstatus.adapters.in.rest.dtos.UpdateCarePlanStatusRequest;
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
@RequestMapping("/api/care-plan-statuses")
public class CarePlanStatusController {
    private final RegisterCarePlanStatusUseCase registerUseCase;
    private final GetCarePlanStatusByIdUseCase getByIdUseCase;
    private final ListCarePlanStatusUseCase listUseCase;
    private final UpdateCarePlanStatusUseCase updateUseCase;
    private final DeleteCarePlanStatusUseCase deleteUseCase;

    public CarePlanStatusController(RegisterCarePlanStatusUseCase registerUseCase,
            GetCarePlanStatusByIdUseCase getByIdUseCase, ListCarePlanStatusUseCase listUseCase,
            UpdateCarePlanStatusUseCase updateUseCase, DeleteCarePlanStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CarePlanStatusResponse> create(@Valid @RequestBody CreateCarePlanStatusRequest request) {
        var command = new RegisterCarePlanStatusCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CarePlanStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarePlanStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CarePlanStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarePlanStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCarePlanStatusRequest request) {
        var command = new UpdateCarePlanStatusCommand(
                new CarePlanStatusId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new CarePlanStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
