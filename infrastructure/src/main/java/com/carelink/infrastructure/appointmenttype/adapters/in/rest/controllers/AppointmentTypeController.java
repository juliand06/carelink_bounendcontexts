package com.carelink.infrastructure.appointmenttype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.appointmenttype.command.RegisterAppointmentTypeCommand;
import com.carelink.application.appointmenttype.command.UpdateAppointmentTypeCommand;
import com.carelink.application.appointmenttype.dto.AppointmentTypeResponse;
import com.carelink.application.appointmenttype.usecase.DeleteAppointmentTypeUseCase;
import com.carelink.application.appointmenttype.usecase.GetAppointmentTypeByIdUseCase;
import com.carelink.application.appointmenttype.usecase.ListAppointmentTypeUseCase;
import com.carelink.application.appointmenttype.usecase.RegisterAppointmentTypeUseCase;
import com.carelink.application.appointmenttype.usecase.UpdateAppointmentTypeUseCase;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.infrastructure.appointmenttype.adapters.in.rest.dtos.CreateAppointmentTypeRequest;
import com.carelink.infrastructure.appointmenttype.adapters.in.rest.dtos.UpdateAppointmentTypeRequest;
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
@RequestMapping("/api/appointment-types")
public class AppointmentTypeController {
    private final RegisterAppointmentTypeUseCase registerUseCase;
    private final GetAppointmentTypeByIdUseCase getByIdUseCase;
    private final ListAppointmentTypeUseCase listUseCase;
    private final UpdateAppointmentTypeUseCase updateUseCase;
    private final DeleteAppointmentTypeUseCase deleteUseCase;

    public AppointmentTypeController(RegisterAppointmentTypeUseCase registerUseCase,
            GetAppointmentTypeByIdUseCase getByIdUseCase, ListAppointmentTypeUseCase listUseCase,
            UpdateAppointmentTypeUseCase updateUseCase, DeleteAppointmentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentTypeResponse> create(@Valid @RequestBody CreateAppointmentTypeRequest request) {
        var command = new RegisterAppointmentTypeCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AppointmentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentTypeRequest request) {
        var command = new UpdateAppointmentTypeCommand(
                new AppointmentTypeId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AppointmentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
