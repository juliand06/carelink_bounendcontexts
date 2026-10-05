package com.carelink.infrastructure.handoff.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.handoff.command.RegisterHandoffCommand;
import com.carelink.application.handoff.command.UpdateHandoffCommand;
import com.carelink.application.handoff.dto.HandoffResponse;
import com.carelink.application.handoff.usecase.DeleteHandoffUseCase;
import com.carelink.application.handoff.usecase.GetHandoffByIdUseCase;
import com.carelink.application.handoff.usecase.ListHandoffUseCase;
import com.carelink.application.handoff.usecase.RegisterHandoffUseCase;
import com.carelink.application.handoff.usecase.UpdateHandoffUseCase;
import com.carelink.domain.handoff.model.valueobject.HandoffId;
import com.carelink.domain.handoffstatus.model.valueobject.HandoffStatusId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.handoff.adapters.in.rest.dtos.CreateHandoffRequest;
import com.carelink.infrastructure.handoff.adapters.in.rest.dtos.UpdateHandoffRequest;
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
@RequestMapping("/api/handoffs")
public class HandoffController {
    private final RegisterHandoffUseCase registerUseCase;
    private final GetHandoffByIdUseCase getByIdUseCase;
    private final ListHandoffUseCase listUseCase;
    private final UpdateHandoffUseCase updateUseCase;
    private final DeleteHandoffUseCase deleteUseCase;

    public HandoffController(RegisterHandoffUseCase registerUseCase,
            GetHandoffByIdUseCase getByIdUseCase, ListHandoffUseCase listUseCase,
            UpdateHandoffUseCase updateUseCase, DeleteHandoffUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<HandoffResponse> create(@Valid @RequestBody CreateHandoffRequest request) {
        var command = new RegisterHandoffCommand(
                new SupportConversationId(request.conversationId()),
                new HandoffStatusId(request.handoffStatusId()),
                request.originatedFromAi(),
                request.handoffReason());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<HandoffResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HandoffResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new HandoffId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HandoffResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateHandoffRequest request) {
        var command = new UpdateHandoffCommand(
                new HandoffId(id),
                new SupportConversationId(request.conversationId()),
                new HandoffStatusId(request.handoffStatusId()),
                request.originatedFromAi(),
                request.handoffReason());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new HandoffId(id));
        return ResponseEntity.noContent().build();
    }
}
