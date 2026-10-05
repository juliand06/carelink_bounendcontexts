package com.carelink.infrastructure.caregoal.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.caregoal.command.RegisterCareGoalCommand;
import com.carelink.application.caregoal.command.UpdateCareGoalCommand;
import com.carelink.application.caregoal.dto.CareGoalResponse;
import com.carelink.application.caregoal.usecase.DeleteCareGoalUseCase;
import com.carelink.application.caregoal.usecase.GetCareGoalByIdUseCase;
import com.carelink.application.caregoal.usecase.ListCareGoalUseCase;
import com.carelink.application.caregoal.usecase.RegisterCareGoalUseCase;
import com.carelink.application.caregoal.usecase.UpdateCareGoalUseCase;
import com.carelink.domain.caregoal.model.valueobject.CareGoalId;
import com.carelink.domain.caregoalstatus.model.valueobject.CareGoalStatusId;
import com.carelink.domain.careplan.model.valueobject.CarePlanId;
import com.carelink.infrastructure.caregoal.adapters.in.rest.dtos.CreateCareGoalRequest;
import com.carelink.infrastructure.caregoal.adapters.in.rest.dtos.UpdateCareGoalRequest;
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
@RequestMapping("/api/care-goals")
public class CareGoalController {
    private final RegisterCareGoalUseCase registerUseCase;
    private final GetCareGoalByIdUseCase getByIdUseCase;
    private final ListCareGoalUseCase listUseCase;
    private final UpdateCareGoalUseCase updateUseCase;
    private final DeleteCareGoalUseCase deleteUseCase;

    public CareGoalController(RegisterCareGoalUseCase registerUseCase,
            GetCareGoalByIdUseCase getByIdUseCase, ListCareGoalUseCase listUseCase,
            UpdateCareGoalUseCase updateUseCase, DeleteCareGoalUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<CareGoalResponse> create(@Valid @RequestBody CreateCareGoalRequest request) {
        var command = new RegisterCareGoalCommand(
                new CarePlanId(request.carePlanId()),
                request.goalDescription(),
                request.targetDate(),
                request.achievedAt(),
                request.progressNotes(),
                new CareGoalStatusId(request.careGoalStatusId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<CareGoalResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CareGoalResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new CareGoalId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CareGoalResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateCareGoalRequest request) {
        var command = new UpdateCareGoalCommand(
                new CareGoalId(id),
                new CarePlanId(request.carePlanId()),
                request.goalDescription(),
                request.targetDate(),
                request.achievedAt(),
                request.progressNotes(),
                new CareGoalStatusId(request.careGoalStatusId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new CareGoalId(id));
        return ResponseEntity.noContent().build();
    }
}
