package com.carelink.infrastructure.appointmentstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.appointmentstatus.command.RegisterAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.command.UpdateAppointmentStatusCommand;
import com.carelink.application.appointmentstatus.dto.AppointmentStatusResponse;
import com.carelink.application.appointmentstatus.usecase.DeleteAppointmentStatusUseCase;
import com.carelink.application.appointmentstatus.usecase.GetAppointmentStatusByIdUseCase;
import com.carelink.application.appointmentstatus.usecase.ListAppointmentStatusUseCase;
import com.carelink.application.appointmentstatus.usecase.RegisterAppointmentStatusUseCase;
import com.carelink.application.appointmentstatus.usecase.UpdateAppointmentStatusUseCase;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.infrastructure.appointmentstatus.adapters.in.rest.dtos.CreateAppointmentStatusRequest;
import com.carelink.infrastructure.appointmentstatus.adapters.in.rest.dtos.UpdateAppointmentStatusRequest;
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
@RequestMapping("/api/appointment-statuses")
public class AppointmentStatusController {
    private final RegisterAppointmentStatusUseCase registerUseCase;
    private final GetAppointmentStatusByIdUseCase getByIdUseCase;
    private final ListAppointmentStatusUseCase listUseCase;
    private final UpdateAppointmentStatusUseCase updateUseCase;
    private final DeleteAppointmentStatusUseCase deleteUseCase;

    public AppointmentStatusController(RegisterAppointmentStatusUseCase registerUseCase,
            GetAppointmentStatusByIdUseCase getByIdUseCase, ListAppointmentStatusUseCase listUseCase,
            UpdateAppointmentStatusUseCase updateUseCase, DeleteAppointmentStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentStatusResponse> create(@Valid @RequestBody CreateAppointmentStatusRequest request) {
        var command = new RegisterAppointmentStatusCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AppointmentStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentStatusRequest request) {
        var command = new UpdateAppointmentStatusCommand(
                new AppointmentStatusId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AppointmentStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
