package com.carelink.infrastructure.llmexecutionerror.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmexecutionerror.command.RegisterLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.command.UpdateLlmExecutionErrorCommand;
import com.carelink.application.llmexecutionerror.dto.LlmExecutionErrorResponse;
import com.carelink.application.llmexecutionerror.usecase.DeleteLlmExecutionErrorUseCase;
import com.carelink.application.llmexecutionerror.usecase.GetLlmExecutionErrorByIdUseCase;
import com.carelink.application.llmexecutionerror.usecase.ListLlmExecutionErrorUseCase;
import com.carelink.application.llmexecutionerror.usecase.RegisterLlmExecutionErrorUseCase;
import com.carelink.application.llmexecutionerror.usecase.UpdateLlmExecutionErrorUseCase;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionerror.model.valueobject.LlmExecutionErrorId;
import com.carelink.infrastructure.llmexecutionerror.adapters.in.rest.dtos.CreateLlmExecutionErrorRequest;
import com.carelink.infrastructure.llmexecutionerror.adapters.in.rest.dtos.UpdateLlmExecutionErrorRequest;
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
@RequestMapping("/api/llm-execution-errors")
public class LlmExecutionErrorController {
    private final RegisterLlmExecutionErrorUseCase registerUseCase;
    private final GetLlmExecutionErrorByIdUseCase getByIdUseCase;
    private final ListLlmExecutionErrorUseCase listUseCase;
    private final UpdateLlmExecutionErrorUseCase updateUseCase;
    private final DeleteLlmExecutionErrorUseCase deleteUseCase;

    public LlmExecutionErrorController(RegisterLlmExecutionErrorUseCase registerUseCase,
            GetLlmExecutionErrorByIdUseCase getByIdUseCase, ListLlmExecutionErrorUseCase listUseCase,
            UpdateLlmExecutionErrorUseCase updateUseCase, DeleteLlmExecutionErrorUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmExecutionErrorResponse> create(@Valid @RequestBody CreateLlmExecutionErrorRequest request) {
        var command = new RegisterLlmExecutionErrorCommand(
                new LlmExecutionId(request.executionId()),
                request.errorDetail(),
                request.errorCode(),
                request.vendorErrorRef());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmExecutionErrorResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmExecutionErrorResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmExecutionErrorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmExecutionErrorResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmExecutionErrorRequest request) {
        var command = new UpdateLlmExecutionErrorCommand(
                new LlmExecutionErrorId(id),
                new LlmExecutionId(request.executionId()),
                request.errorDetail(),
                request.errorCode(),
                request.vendorErrorRef());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmExecutionErrorId(id));
        return ResponseEntity.noContent().build();
    }
}
