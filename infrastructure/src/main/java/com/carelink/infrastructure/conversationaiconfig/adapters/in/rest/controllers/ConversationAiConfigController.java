package com.carelink.infrastructure.conversationaiconfig.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.conversationaiconfig.command.RegisterConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.command.UpdateConversationAiConfigCommand;
import com.carelink.application.conversationaiconfig.dto.ConversationAiConfigResponse;
import com.carelink.application.conversationaiconfig.usecase.DeleteConversationAiConfigUseCase;
import com.carelink.application.conversationaiconfig.usecase.GetConversationAiConfigByIdUseCase;
import com.carelink.application.conversationaiconfig.usecase.ListConversationAiConfigUseCase;
import com.carelink.application.conversationaiconfig.usecase.RegisterConversationAiConfigUseCase;
import com.carelink.application.conversationaiconfig.usecase.UpdateConversationAiConfigUseCase;
import com.carelink.domain.conversationaiconfig.model.valueobject.ConversationAiConfigId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.infrastructure.conversationaiconfig.adapters.in.rest.dtos.CreateConversationAiConfigRequest;
import com.carelink.infrastructure.conversationaiconfig.adapters.in.rest.dtos.UpdateConversationAiConfigRequest;
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
@RequestMapping("/api/conversation-ai-configs")
public class ConversationAiConfigController {
    private final RegisterConversationAiConfigUseCase registerUseCase;
    private final GetConversationAiConfigByIdUseCase getByIdUseCase;
    private final ListConversationAiConfigUseCase listUseCase;
    private final UpdateConversationAiConfigUseCase updateUseCase;
    private final DeleteConversationAiConfigUseCase deleteUseCase;

    public ConversationAiConfigController(RegisterConversationAiConfigUseCase registerUseCase,
            GetConversationAiConfigByIdUseCase getByIdUseCase, ListConversationAiConfigUseCase listUseCase,
            UpdateConversationAiConfigUseCase updateUseCase, DeleteConversationAiConfigUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ConversationAiConfigResponse> create(@Valid @RequestBody CreateConversationAiConfigRequest request) {
        var command = new RegisterConversationAiConfigCommand(
                new SupportConversationId(request.conversationId()),
                request.aiEnabled(),
                new LlmModelId(request.preferredModelId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ConversationAiConfigResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversationAiConfigResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ConversationAiConfigId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversationAiConfigResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateConversationAiConfigRequest request) {
        var command = new UpdateConversationAiConfigCommand(
                new ConversationAiConfigId(id),
                new SupportConversationId(request.conversationId()),
                request.aiEnabled(),
                new LlmModelId(request.preferredModelId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ConversationAiConfigId(id));
        return ResponseEntity.noContent().build();
    }
}
