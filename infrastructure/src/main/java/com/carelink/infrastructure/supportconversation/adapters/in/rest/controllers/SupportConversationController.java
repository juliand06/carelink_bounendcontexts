package com.carelink.infrastructure.supportconversation.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.supportconversation.command.RegisterSupportConversationCommand;
import com.carelink.application.supportconversation.command.UpdateSupportConversationCommand;
import com.carelink.application.supportconversation.dto.SupportConversationResponse;
import com.carelink.application.supportconversation.usecase.DeleteSupportConversationUseCase;
import com.carelink.application.supportconversation.usecase.GetSupportConversationByIdUseCase;
import com.carelink.application.supportconversation.usecase.ListSupportConversationUseCase;
import com.carelink.application.supportconversation.usecase.RegisterSupportConversationUseCase;
import com.carelink.application.supportconversation.usecase.UpdateSupportConversationUseCase;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportconversationstatus.model.valueobject.SupportConversationStatusId;
import com.carelink.domain.urgencylevel.model.valueobject.UrgencyLevelId;
import com.carelink.infrastructure.supportconversation.adapters.in.rest.dtos.CreateSupportConversationRequest;
import com.carelink.infrastructure.supportconversation.adapters.in.rest.dtos.UpdateSupportConversationRequest;
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
@RequestMapping("/api/support-conversations")
public class SupportConversationController {
    private final RegisterSupportConversationUseCase registerUseCase;
    private final GetSupportConversationByIdUseCase getByIdUseCase;
    private final ListSupportConversationUseCase listUseCase;
    private final UpdateSupportConversationUseCase updateUseCase;
    private final DeleteSupportConversationUseCase deleteUseCase;

    public SupportConversationController(RegisterSupportConversationUseCase registerUseCase,
            GetSupportConversationByIdUseCase getByIdUseCase, ListSupportConversationUseCase listUseCase,
            UpdateSupportConversationUseCase updateUseCase, DeleteSupportConversationUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SupportConversationResponse> create(@Valid @RequestBody CreateSupportConversationRequest request) {
        var command = new RegisterSupportConversationCommand(
                new SupportConversationStatusId(request.conversationStatusId()),
                new UrgencyLevelId(request.urgencyLevelId()),
                request.lastInteractionAt(),
                request.isClosed(),
                request.closedAt(),
                request.closedBy());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SupportConversationResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportConversationResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SupportConversationId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportConversationResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSupportConversationRequest request) {
        var command = new UpdateSupportConversationCommand(
                new SupportConversationId(id),
                new SupportConversationStatusId(request.conversationStatusId()),
                new UrgencyLevelId(request.urgencyLevelId()),
                request.lastInteractionAt(),
                request.isClosed(),
                request.closedAt(),
                request.closedBy());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SupportConversationId(id));
        return ResponseEntity.noContent().build();
    }
}
