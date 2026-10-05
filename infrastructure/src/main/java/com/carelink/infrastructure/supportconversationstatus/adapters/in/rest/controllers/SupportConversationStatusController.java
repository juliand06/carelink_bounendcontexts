package com.carelink.infrastructure.supportconversationstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.supportconversationstatus.command.RegisterSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.command.UpdateSupportConversationStatusCommand;
import com.carelink.application.supportconversationstatus.dto.SupportConversationStatusResponse;
import com.carelink.application.supportconversationstatus.usecase.DeleteSupportConversationStatusUseCase;
import com.carelink.application.supportconversationstatus.usecase.GetSupportConversationStatusByIdUseCase;
import com.carelink.application.supportconversationstatus.usecase.ListSupportConversationStatusUseCase;
import com.carelink.application.supportconversationstatus.usecase.RegisterSupportConversationStatusUseCase;
import com.carelink.application.supportconversationstatus.usecase.UpdateSupportConversationStatusUseCase;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.infrastructure.supportconversationstatus.adapters.in.rest.dtos.CreateSupportConversationStatusRequest;
import com.carelink.infrastructure.supportconversationstatus.adapters.in.rest.dtos.UpdateSupportConversationStatusRequest;
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
@RequestMapping("/api/support-conversation-statuses")
public class SupportConversationStatusController {
    private final RegisterSupportConversationStatusUseCase registerUseCase;
    private final GetSupportConversationStatusByIdUseCase getByIdUseCase;
    private final ListSupportConversationStatusUseCase listUseCase;
    private final UpdateSupportConversationStatusUseCase updateUseCase;
    private final DeleteSupportConversationStatusUseCase deleteUseCase;

    public SupportConversationStatusController(RegisterSupportConversationStatusUseCase registerUseCase,
            GetSupportConversationStatusByIdUseCase getByIdUseCase, ListSupportConversationStatusUseCase listUseCase,
            UpdateSupportConversationStatusUseCase updateUseCase, DeleteSupportConversationStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SupportConversationStatusResponse> create(@Valid @RequestBody CreateSupportConversationStatusRequest request) {
        var command = new RegisterSupportConversationStatusCommand(
                request.code(),
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SupportConversationStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportConversationStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SupportConversationStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportConversationStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSupportConversationStatusRequest request) {
        var command = new UpdateSupportConversationStatusCommand(
                new SupportConversationStatusId(id),
                request.code(),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SupportConversationStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
