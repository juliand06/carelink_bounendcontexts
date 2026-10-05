package com.carelink.infrastructure.dangerassessment.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.dangerassessment.command.RegisterDangerAssessmentCommand;
import com.carelink.application.dangerassessment.command.UpdateDangerAssessmentCommand;
import com.carelink.application.dangerassessment.dto.DangerAssessmentResponse;
import com.carelink.application.dangerassessment.usecase.DeleteDangerAssessmentUseCase;
import com.carelink.application.dangerassessment.usecase.GetDangerAssessmentByIdUseCase;
import com.carelink.application.dangerassessment.usecase.ListDangerAssessmentUseCase;
import com.carelink.application.dangerassessment.usecase.RegisterDangerAssessmentUseCase;
import com.carelink.application.dangerassessment.usecase.UpdateDangerAssessmentUseCase;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.dangerassessment.model.valueobject.DangerAssessmentId;
import com.carelink.domain.dangerlevel.model.valueobject.DangerLevelId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.dangerassessment.adapters.in.rest.dtos.CreateDangerAssessmentRequest;
import com.carelink.infrastructure.dangerassessment.adapters.in.rest.dtos.UpdateDangerAssessmentRequest;
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
@RequestMapping("/api/danger-assessments")
public class DangerAssessmentController {
    private final RegisterDangerAssessmentUseCase registerUseCase;
    private final GetDangerAssessmentByIdUseCase getByIdUseCase;
    private final ListDangerAssessmentUseCase listUseCase;
    private final UpdateDangerAssessmentUseCase updateUseCase;
    private final DeleteDangerAssessmentUseCase deleteUseCase;

    public DangerAssessmentController(RegisterDangerAssessmentUseCase registerUseCase,
            GetDangerAssessmentByIdUseCase getByIdUseCase, ListDangerAssessmentUseCase listUseCase,
            UpdateDangerAssessmentUseCase updateUseCase, DeleteDangerAssessmentUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<DangerAssessmentResponse> create(@Valid @RequestBody CreateDangerAssessmentRequest request) {
        var command = new RegisterDangerAssessmentCommand(
                new AppointmentId(request.appointmentId()),
                new DangerLevelId(request.dangerLevelId()),
                request.suicidalIdeation(),
                request.hasSuicidePlan(),
                request.hasSuicideIntent(),
                request.selfHarmBehavior(),
                request.riskToOthers(),
                request.protectiveFactors(),
                request.identifiedRiskFactors(),
                request.clinicalActionsTaken(),
                request.evaluatorObservations(),
                new SpecialistId(request.evaluatedBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<DangerAssessmentResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DangerAssessmentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new DangerAssessmentId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DangerAssessmentResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateDangerAssessmentRequest request) {
        var command = new UpdateDangerAssessmentCommand(
                new DangerAssessmentId(id),
                new AppointmentId(request.appointmentId()),
                new DangerLevelId(request.dangerLevelId()),
                request.suicidalIdeation(),
                request.hasSuicidePlan(),
                request.hasSuicideIntent(),
                request.selfHarmBehavior(),
                request.riskToOthers(),
                request.protectiveFactors(),
                request.identifiedRiskFactors(),
                request.clinicalActionsTaken(),
                request.evaluatorObservations(),
                new SpecialistId(request.evaluatedBy()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new DangerAssessmentId(id));
        return ResponseEntity.noContent().build();
    }
}
