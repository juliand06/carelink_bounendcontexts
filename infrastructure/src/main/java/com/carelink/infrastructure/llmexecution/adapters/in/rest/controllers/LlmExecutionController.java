package com.carelink.infrastructure.llmexecution.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmexecution.command.RegisterLlmExecutionCommand;
import com.carelink.application.llmexecution.command.UpdateLlmExecutionCommand;
import com.carelink.application.llmexecution.dto.LlmExecutionResponse;
import com.carelink.application.llmexecution.usecase.DeleteLlmExecutionUseCase;
import com.carelink.application.llmexecution.usecase.GetLlmExecutionByIdUseCase;
import com.carelink.application.llmexecution.usecase.ListLlmExecutionUseCase;
import com.carelink.application.llmexecution.usecase.RegisterLlmExecutionUseCase;
import com.carelink.application.llmexecution.usecase.UpdateLlmExecutionUseCase;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.supportconversation.model.valueobject.SupportConversationId;
import com.carelink.domain.supportmessage.model.valueobject.SupportMessageId;
import com.carelink.infrastructure.llmexecution.adapters.in.rest.dtos.CreateLlmExecutionRequest;
import com.carelink.infrastructure.llmexecution.adapters.in.rest.dtos.UpdateLlmExecutionRequest;
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
@RequestMapping("/api/llm-executions")
public class LlmExecutionController {
    private final RegisterLlmExecutionUseCase registerUseCase;
    private final GetLlmExecutionByIdUseCase getByIdUseCase;
    private final ListLlmExecutionUseCase listUseCase;
    private final UpdateLlmExecutionUseCase updateUseCase;
    private final DeleteLlmExecutionUseCase deleteUseCase;

    public LlmExecutionController(RegisterLlmExecutionUseCase registerUseCase,
            GetLlmExecutionByIdUseCase getByIdUseCase, ListLlmExecutionUseCase listUseCase,
            UpdateLlmExecutionUseCase updateUseCase, DeleteLlmExecutionUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmExecutionResponse> create(@Valid @RequestBody CreateLlmExecutionRequest request) {
        var command = new RegisterLlmExecutionCommand(
                new SupportConversationId(request.conversationId()),
                new SupportMessageId(request.messageId()),
                new LlmModelId(request.modelId()),
                new LlmExecutionStatusId(request.executionStatusId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmExecutionResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmExecutionResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmExecutionId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmExecutionResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmExecutionRequest request) {
        var command = new UpdateLlmExecutionCommand(
                new LlmExecutionId(id),
                new SupportConversationId(request.conversationId()),
                new SupportMessageId(request.messageId()),
                new LlmModelId(request.modelId()),
                new LlmExecutionStatusId(request.executionStatusId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmExecutionId(id));
        return ResponseEntity.noContent().build();
    }
}
