package com.carelink.infrastructure.urgencylevel.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.urgencylevel.command.RegisterUrgencyLevelCommand;
import com.carelink.application.urgencylevel.command.UpdateUrgencyLevelCommand;
import com.carelink.application.urgencylevel.dto.UrgencyLevelResponse;
import com.carelink.application.urgencylevel.usecase.DeleteUrgencyLevelUseCase;
import com.carelink.application.urgencylevel.usecase.GetUrgencyLevelByIdUseCase;
import com.carelink.application.urgencylevel.usecase.ListUrgencyLevelUseCase;
import com.carelink.application.urgencylevel.usecase.RegisterUrgencyLevelUseCase;
import com.carelink.application.urgencylevel.usecase.UpdateUrgencyLevelUseCase;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.infrastructure.urgencylevel.adapters.in.rest.dtos.CreateUrgencyLevelRequest;
import com.carelink.infrastructure.urgencylevel.adapters.in.rest.dtos.UpdateUrgencyLevelRequest;
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
@RequestMapping("/api/urgency-levels")
public class UrgencyLevelController {
    private final RegisterUrgencyLevelUseCase registerUseCase;
    private final GetUrgencyLevelByIdUseCase getByIdUseCase;
    private final ListUrgencyLevelUseCase listUseCase;
    private final UpdateUrgencyLevelUseCase updateUseCase;
    private final DeleteUrgencyLevelUseCase deleteUseCase;

    public UrgencyLevelController(RegisterUrgencyLevelUseCase registerUseCase,
            GetUrgencyLevelByIdUseCase getByIdUseCase, ListUrgencyLevelUseCase listUseCase,
            UpdateUrgencyLevelUseCase updateUseCase, DeleteUrgencyLevelUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<UrgencyLevelResponse> create(@Valid @RequestBody CreateUrgencyLevelRequest request) {
        var command = new RegisterUrgencyLevelCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<UrgencyLevelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UrgencyLevelResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new UrgencyLevelId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UrgencyLevelResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateUrgencyLevelRequest request) {
        var command = new UpdateUrgencyLevelCommand(
                new UrgencyLevelId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new UrgencyLevelId(id));
        return ResponseEntity.noContent().build();
    }
}
