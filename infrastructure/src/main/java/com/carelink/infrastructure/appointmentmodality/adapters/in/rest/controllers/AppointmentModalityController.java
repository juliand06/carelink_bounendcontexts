package com.carelink.infrastructure.appointmentmodality.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.appointmentmodality.command.RegisterAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.command.UpdateAppointmentModalityCommand;
import com.carelink.application.appointmentmodality.dto.AppointmentModalityResponse;
import com.carelink.application.appointmentmodality.usecase.DeleteAppointmentModalityUseCase;
import com.carelink.application.appointmentmodality.usecase.GetAppointmentModalityByIdUseCase;
import com.carelink.application.appointmentmodality.usecase.ListAppointmentModalityUseCase;
import com.carelink.application.appointmentmodality.usecase.RegisterAppointmentModalityUseCase;
import com.carelink.application.appointmentmodality.usecase.UpdateAppointmentModalityUseCase;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.infrastructure.appointmentmodality.adapters.in.rest.dtos.CreateAppointmentModalityRequest;
import com.carelink.infrastructure.appointmentmodality.adapters.in.rest.dtos.UpdateAppointmentModalityRequest;
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
@RequestMapping("/api/appointment-modalities")
public class AppointmentModalityController {
    private final RegisterAppointmentModalityUseCase registerUseCase;
    private final GetAppointmentModalityByIdUseCase getByIdUseCase;
    private final ListAppointmentModalityUseCase listUseCase;
    private final UpdateAppointmentModalityUseCase updateUseCase;
    private final DeleteAppointmentModalityUseCase deleteUseCase;

    public AppointmentModalityController(RegisterAppointmentModalityUseCase registerUseCase,
            GetAppointmentModalityByIdUseCase getByIdUseCase, ListAppointmentModalityUseCase listUseCase,
            UpdateAppointmentModalityUseCase updateUseCase, DeleteAppointmentModalityUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentModalityResponse> create(@Valid @RequestBody CreateAppointmentModalityRequest request) {
        var command = new RegisterAppointmentModalityCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentModalityResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentModalityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AppointmentModalityId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentModalityResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentModalityRequest request) {
        var command = new UpdateAppointmentModalityCommand(
                new AppointmentModalityId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AppointmentModalityId(id));
        return ResponseEntity.noContent().build();
    }
}
