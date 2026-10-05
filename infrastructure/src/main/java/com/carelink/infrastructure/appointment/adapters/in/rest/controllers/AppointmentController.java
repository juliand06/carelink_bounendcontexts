package com.carelink.infrastructure.appointment.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.appointment.command.RegisterAppointmentCommand;
import com.carelink.application.appointment.command.UpdateAppointmentCommand;
import com.carelink.application.appointment.dto.AppointmentResponse;
import com.carelink.application.appointment.usecase.DeleteAppointmentUseCase;
import com.carelink.application.appointment.usecase.GetAppointmentByIdUseCase;
import com.carelink.application.appointment.usecase.ListAppointmentUseCase;
import com.carelink.application.appointment.usecase.RegisterAppointmentUseCase;
import com.carelink.application.appointment.usecase.UpdateAppointmentUseCase;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.appointmentmodality.model.valueobject.AppointmentModalityId;
import com.carelink.domain.appointmentstatus.model.valueobject.AppointmentStatusId;
import com.carelink.domain.appointmenttype.model.valueobject.AppointmentTypeId;
import com.carelink.domain.healthrecord.model.valueobject.HealthRecordId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.appointment.adapters.in.rest.dtos.CreateAppointmentRequest;
import com.carelink.infrastructure.appointment.adapters.in.rest.dtos.UpdateAppointmentRequest;
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
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final RegisterAppointmentUseCase registerUseCase;
    private final GetAppointmentByIdUseCase getByIdUseCase;
    private final ListAppointmentUseCase listUseCase;
    private final UpdateAppointmentUseCase updateUseCase;
    private final DeleteAppointmentUseCase deleteUseCase;

    public AppointmentController(RegisterAppointmentUseCase registerUseCase,
            GetAppointmentByIdUseCase getByIdUseCase, ListAppointmentUseCase listUseCase,
            UpdateAppointmentUseCase updateUseCase, DeleteAppointmentUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody CreateAppointmentRequest request) {
        var command = new RegisterAppointmentCommand(
                new HealthRecordId(request.healthRecordId()),
                new SpecialistId(request.specialistId()),
                new AppointmentTypeId(request.appointmentTypeId()),
                request.startedAt(),
                request.endedAt(),
                request.chiefComplaint(),
                request.currentCondition(),
                new AppointmentModalityId(request.modalityId()),
                new AppointmentStatusId(request.statusId()),
                new SpecialistId(request.createdBy()),
                request.updatedBy() == null ? null : new SpecialistId(request.updatedBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new AppointmentId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateAppointmentRequest request) {
        var command = new UpdateAppointmentCommand(
                new AppointmentId(id),
                new HealthRecordId(request.healthRecordId()),
                new SpecialistId(request.specialistId()),
                new AppointmentTypeId(request.appointmentTypeId()),
                request.startedAt(),
                request.endedAt(),
                request.chiefComplaint(),
                request.currentCondition(),
                new AppointmentModalityId(request.modalityId()),
                new AppointmentStatusId(request.statusId()),
                request.updatedBy() == null ? null : new SpecialistId(request.updatedBy()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new AppointmentId(id));
        return ResponseEntity.noContent().build();
    }
}
