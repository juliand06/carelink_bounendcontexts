package com.carelink.infrastructure.mentalstateevaluation.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.mentalstateevaluation.command.RegisterMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.command.UpdateMentalStateEvaluationCommand;
import com.carelink.application.mentalstateevaluation.dto.MentalStateEvaluationResponse;
import com.carelink.application.mentalstateevaluation.usecase.DeleteMentalStateEvaluationUseCase;
import com.carelink.application.mentalstateevaluation.usecase.GetMentalStateEvaluationByIdUseCase;
import com.carelink.application.mentalstateevaluation.usecase.ListMentalStateEvaluationUseCase;
import com.carelink.application.mentalstateevaluation.usecase.RegisterMentalStateEvaluationUseCase;
import com.carelink.application.mentalstateevaluation.usecase.UpdateMentalStateEvaluationUseCase;
import com.carelink.domain.appointment.model.valueobject.AppointmentId;
import com.carelink.domain.mentalstateevaluation.model.valueobject.MentalStateEvaluationId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.mentalstateevaluation.adapters.in.rest.dtos.CreateMentalStateEvaluationRequest;
import com.carelink.infrastructure.mentalstateevaluation.adapters.in.rest.dtos.UpdateMentalStateEvaluationRequest;
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
@RequestMapping("/api/mental-state-evaluations")
public class MentalStateEvaluationController {
    private final RegisterMentalStateEvaluationUseCase registerUseCase;
    private final GetMentalStateEvaluationByIdUseCase getByIdUseCase;
    private final ListMentalStateEvaluationUseCase listUseCase;
    private final UpdateMentalStateEvaluationUseCase updateUseCase;
    private final DeleteMentalStateEvaluationUseCase deleteUseCase;

    public MentalStateEvaluationController(RegisterMentalStateEvaluationUseCase registerUseCase,
            GetMentalStateEvaluationByIdUseCase getByIdUseCase, ListMentalStateEvaluationUseCase listUseCase,
            UpdateMentalStateEvaluationUseCase updateUseCase, DeleteMentalStateEvaluationUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<MentalStateEvaluationResponse> create(@Valid @RequestBody CreateMentalStateEvaluationRequest request) {
        var command = new RegisterMentalStateEvaluationCommand(
                new AppointmentId(request.appointmentId()),
                request.generalAppearance(),
                request.behavioralObservation(),
                request.attitudeTowardExaminer(),
                request.levelOfConsciousness(),
                request.orientationStatus(),
                request.attentionAndConcentration(),
                request.memoryAssessment(),
                request.speechCharacteristics(),
                request.moodDescription(),
                request.affectDescription(),
                request.thoughtProcess(),
                request.thoughtContent(),
                request.perceptualDisturbances(),
                request.judgmentAssessment(),
                request.insightAssessment(),
                request.psychomotorFindings(),
                request.examinerRemarks(),
                new SpecialistId(request.createdBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<MentalStateEvaluationResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MentalStateEvaluationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new MentalStateEvaluationId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MentalStateEvaluationResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateMentalStateEvaluationRequest request) {
        var command = new UpdateMentalStateEvaluationCommand(
                new MentalStateEvaluationId(id),
                new AppointmentId(request.appointmentId()),
                request.generalAppearance(),
                request.behavioralObservation(),
                request.attitudeTowardExaminer(),
                request.levelOfConsciousness(),
                request.orientationStatus(),
                request.attentionAndConcentration(),
                request.memoryAssessment(),
                request.speechCharacteristics(),
                request.moodDescription(),
                request.affectDescription(),
                request.thoughtProcess(),
                request.thoughtContent(),
                request.perceptualDisturbances(),
                request.judgmentAssessment(),
                request.insightAssessment(),
                request.psychomotorFindings(),
                request.examinerRemarks());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new MentalStateEvaluationId(id));
        return ResponseEntity.noContent().build();
    }
}
