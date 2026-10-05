package com.carelink.infrastructure.evaluationinstrumenttype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.evaluationinstrumenttype.command.RegisterEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.command.UpdateEvaluationInstrumentTypeCommand;
import com.carelink.application.evaluationinstrumenttype.dto.EvaluationInstrumentTypeResponse;
import com.carelink.application.evaluationinstrumenttype.usecase.DeleteEvaluationInstrumentTypeUseCase;
import com.carelink.application.evaluationinstrumenttype.usecase.GetEvaluationInstrumentTypeByIdUseCase;
import com.carelink.application.evaluationinstrumenttype.usecase.ListEvaluationInstrumentTypeUseCase;
import com.carelink.application.evaluationinstrumenttype.usecase.RegisterEvaluationInstrumentTypeUseCase;
import com.carelink.application.evaluationinstrumenttype.usecase.UpdateEvaluationInstrumentTypeUseCase;
import com.carelink.domain.evaluationinstrumenttype.model.valueobject.EvaluationInstrumentTypeId;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.in.rest.dtos.CreateEvaluationInstrumentTypeRequest;
import com.carelink.infrastructure.evaluationinstrumenttype.adapters.in.rest.dtos.UpdateEvaluationInstrumentTypeRequest;
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
@RequestMapping("/api/evaluation-instrument-types")
public class EvaluationInstrumentTypeController {
    private final RegisterEvaluationInstrumentTypeUseCase registerUseCase;
    private final GetEvaluationInstrumentTypeByIdUseCase getByIdUseCase;
    private final ListEvaluationInstrumentTypeUseCase listUseCase;
    private final UpdateEvaluationInstrumentTypeUseCase updateUseCase;
    private final DeleteEvaluationInstrumentTypeUseCase deleteUseCase;

    public EvaluationInstrumentTypeController(RegisterEvaluationInstrumentTypeUseCase registerUseCase,
            GetEvaluationInstrumentTypeByIdUseCase getByIdUseCase, ListEvaluationInstrumentTypeUseCase listUseCase,
            UpdateEvaluationInstrumentTypeUseCase updateUseCase, DeleteEvaluationInstrumentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<EvaluationInstrumentTypeResponse> create(@Valid @RequestBody CreateEvaluationInstrumentTypeRequest request) {
        var command = new RegisterEvaluationInstrumentTypeCommand(
                request.code(),
                request.label(),
                request.active(),
                request.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<EvaluationInstrumentTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluationInstrumentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new EvaluationInstrumentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvaluationInstrumentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateEvaluationInstrumentTypeRequest request) {
        var command = new UpdateEvaluationInstrumentTypeCommand(
                new EvaluationInstrumentTypeId(id),
                request.code(),
                request.label(),
                request.active(),
                request.description());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new EvaluationInstrumentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
