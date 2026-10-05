package com.carelink.infrastructure.handoffstatusaudit.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.handoffstatusaudit.command.RegisterHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.command.UpdateHandoffStatusAuditCommand;
import com.carelink.application.handoffstatusaudit.dto.HandoffStatusAuditResponse;
import com.carelink.application.handoffstatusaudit.usecase.DeleteHandoffStatusAuditUseCase;
import com.carelink.application.handoffstatusaudit.usecase.GetHandoffStatusAuditByIdUseCase;
import com.carelink.application.handoffstatusaudit.usecase.ListHandoffStatusAuditUseCase;
import com.carelink.application.handoffstatusaudit.usecase.RegisterHandoffStatusAuditUseCase;
import com.carelink.application.handoffstatusaudit.usecase.UpdateHandoffStatusAuditUseCase;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.handoffstatusaudit.model.valueobject.HandoffStatusAuditId;
import com.carelink.infrastructure.handoffstatusaudit.adapters.in.rest.dtos.CreateHandoffStatusAuditRequest;
import com.carelink.infrastructure.handoffstatusaudit.adapters.in.rest.dtos.UpdateHandoffStatusAuditRequest;
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
@RequestMapping("/api/handoff-status-audit")
public class HandoffStatusAuditController {
    private final RegisterHandoffStatusAuditUseCase registerUseCase;
    private final GetHandoffStatusAuditByIdUseCase getByIdUseCase;
    private final ListHandoffStatusAuditUseCase listUseCase;
    private final UpdateHandoffStatusAuditUseCase updateUseCase;
    private final DeleteHandoffStatusAuditUseCase deleteUseCase;

    public HandoffStatusAuditController(RegisterHandoffStatusAuditUseCase registerUseCase,
            GetHandoffStatusAuditByIdUseCase getByIdUseCase, ListHandoffStatusAuditUseCase listUseCase,
            UpdateHandoffStatusAuditUseCase updateUseCase, DeleteHandoffStatusAuditUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HandoffStatusAuditResponse> create(@Valid @RequestBody CreateHandoffStatusAuditRequest request) {
        var command = new RegisterHandoffStatusAuditCommand(
                new HandoffId(request.handoffId()),
                new HandoffStatusId(request.handoffStatusId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HandoffStatusAuditResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandoffStatusAuditResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HandoffStatusAuditId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HandoffStatusAuditResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHandoffStatusAuditRequest request) {
        var command = new UpdateHandoffStatusAuditCommand(
                new HandoffStatusAuditId(id),
                new HandoffId(request.handoffId()),
                new HandoffStatusId(request.handoffStatusId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HandoffStatusAuditId(id));
        return ResponseEntity.noContent().build();
    }
}
