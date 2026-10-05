package com.carelink.infrastructure.llmexecutionstatus.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmexecutionstatus.command.RegisterLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.command.UpdateLlmExecutionStatusCommand;
import com.carelink.application.llmexecutionstatus.dto.LlmExecutionStatusResponse;
import com.carelink.application.llmexecutionstatus.usecase.DeleteLlmExecutionStatusUseCase;
import com.carelink.application.llmexecutionstatus.usecase.GetLlmExecutionStatusByIdUseCase;
import com.carelink.application.llmexecutionstatus.usecase.ListLlmExecutionStatusUseCase;
import com.carelink.application.llmexecutionstatus.usecase.RegisterLlmExecutionStatusUseCase;
import com.carelink.application.llmexecutionstatus.usecase.UpdateLlmExecutionStatusUseCase;
import com.carelink.domain.llmexecutionstatus.model.valueobject.LlmExecutionStatusId;
import com.carelink.infrastructure.llmexecutionstatus.adapters.in.rest.dtos.CreateLlmExecutionStatusRequest;
import com.carelink.infrastructure.llmexecutionstatus.adapters.in.rest.dtos.UpdateLlmExecutionStatusRequest;
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
@RequestMapping("/api/llm-execution-statuses")
public class LlmExecutionStatusController {
    private final RegisterLlmExecutionStatusUseCase registerUseCase;
    private final GetLlmExecutionStatusByIdUseCase getByIdUseCase;
    private final ListLlmExecutionStatusUseCase listUseCase;
    private final UpdateLlmExecutionStatusUseCase updateUseCase;
    private final DeleteLlmExecutionStatusUseCase deleteUseCase;

    public LlmExecutionStatusController(RegisterLlmExecutionStatusUseCase registerUseCase,
            GetLlmExecutionStatusByIdUseCase getByIdUseCase, ListLlmExecutionStatusUseCase listUseCase,
            UpdateLlmExecutionStatusUseCase updateUseCase, DeleteLlmExecutionStatusUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmExecutionStatusResponse> create(@Valid @RequestBody CreateLlmExecutionStatusRequest request) {
        var command = new RegisterLlmExecutionStatusCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmExecutionStatusResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmExecutionStatusResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmExecutionStatusId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmExecutionStatusResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmExecutionStatusRequest request) {
        var command = new UpdateLlmExecutionStatusCommand(
                new LlmExecutionStatusId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmExecutionStatusId(id));
        return ResponseEntity.noContent().build();
    }
}
