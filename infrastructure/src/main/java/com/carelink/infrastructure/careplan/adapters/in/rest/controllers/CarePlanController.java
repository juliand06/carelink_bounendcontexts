package com.carelink.infrastructure.careplan.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.careplan.command.RegisterCarePlanCommand;
import com.carelink.application.careplan.command.UpdateCarePlanCommand;
import com.carelink.application.careplan.dto.CarePlanResponse;
import com.carelink.application.careplan.usecase.DeleteCarePlanUseCase;
import com.carelink.application.careplan.usecase.GetCarePlanByIdUseCase;
import com.carelink.application.careplan.usecase.ListCarePlanUseCase;
import com.carelink.application.careplan.usecase.RegisterCarePlanUseCase;
import com.carelink.application.careplan.usecase.UpdateCarePlanUseCase;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.domain.careplanstatus.model.valueobject.CarePlanStatusId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.careplan.adapters.in.rest.dtos.CreateCarePlanRequest;
import com.carelink.infrastructure.careplan.adapters.in.rest.dtos.UpdateCarePlanRequest;
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
@RequestMapping("/api/care-plans")
public class CarePlanController {
    private final RegisterCarePlanUseCase registerUseCase;
    private final GetCarePlanByIdUseCase getByIdUseCase;
    private final ListCarePlanUseCase listUseCase;
    private final UpdateCarePlanUseCase updateUseCase;
    private final DeleteCarePlanUseCase deleteUseCase;

    public CarePlanController(RegisterCarePlanUseCase registerUseCase,
            GetCarePlanByIdUseCase getByIdUseCase, ListCarePlanUseCase listUseCase,
            UpdateCarePlanUseCase updateUseCase, DeleteCarePlanUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CarePlanResponse> create(@Valid @RequestBody CreateCarePlanRequest request) {
        var command = new RegisterCarePlanCommand(
                new AppointmentId(request.appointmentId()),
                new SpecialistId(request.specialistId()),
                request.planTitle(),
                request.planDescription(),
                request.startDate(),
                request.projectedEndDate(),
                new CarePlanStatusId(request.carePlanStatusId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CarePlanResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarePlanResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CarePlanId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarePlanResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCarePlanRequest request) {
        var command = new UpdateCarePlanCommand(
                new CarePlanId(id),
                new AppointmentId(request.appointmentId()),
                new SpecialistId(request.specialistId()),
                request.planTitle(),
                request.planDescription(),
                request.startDate(),
                request.projectedEndDate(),
                new CarePlanStatusId(request.carePlanStatusId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new CarePlanId(id));
        return ResponseEntity.noContent().build();
    }
}
