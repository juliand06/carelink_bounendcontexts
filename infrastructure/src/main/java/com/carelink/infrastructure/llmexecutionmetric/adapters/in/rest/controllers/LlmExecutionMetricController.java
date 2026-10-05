package com.carelink.infrastructure.llmexecutionmetric.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmexecutionmetric.command.RegisterLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.command.UpdateLlmExecutionMetricCommand;
import com.carelink.application.llmexecutionmetric.dto.LlmExecutionMetricResponse;
import com.carelink.application.llmexecutionmetric.usecase.DeleteLlmExecutionMetricUseCase;
import com.carelink.application.llmexecutionmetric.usecase.GetLlmExecutionMetricByIdUseCase;
import com.carelink.application.llmexecutionmetric.usecase.ListLlmExecutionMetricUseCase;
import com.carelink.application.llmexecutionmetric.usecase.RegisterLlmExecutionMetricUseCase;
import com.carelink.application.llmexecutionmetric.usecase.UpdateLlmExecutionMetricUseCase;
import com.carelink.domain.llmexecution.model.valueobject.LlmExecutionId;
import com.carelink.domain.llmexecutionmetric.model.valueobject.LlmExecutionMetricId;
import com.carelink.infrastructure.llmexecutionmetric.adapters.in.rest.dtos.CreateLlmExecutionMetricRequest;
import com.carelink.infrastructure.llmexecutionmetric.adapters.in.rest.dtos.UpdateLlmExecutionMetricRequest;
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
@RequestMapping("/api/llm-execution-metrics")
public class LlmExecutionMetricController {
    private final RegisterLlmExecutionMetricUseCase registerUseCase;
    private final GetLlmExecutionMetricByIdUseCase getByIdUseCase;
    private final ListLlmExecutionMetricUseCase listUseCase;
    private final UpdateLlmExecutionMetricUseCase updateUseCase;
    private final DeleteLlmExecutionMetricUseCase deleteUseCase;

    public LlmExecutionMetricController(RegisterLlmExecutionMetricUseCase registerUseCase,
            GetLlmExecutionMetricByIdUseCase getByIdUseCase, ListLlmExecutionMetricUseCase listUseCase,
            UpdateLlmExecutionMetricUseCase updateUseCase, DeleteLlmExecutionMetricUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmExecutionMetricResponse> create(@Valid @RequestBody CreateLlmExecutionMetricRequest request) {
        var command = new RegisterLlmExecutionMetricCommand(
                new LlmExecutionId(request.executionId()),
                request.inputTokens(),
                request.outputTokens(),
                request.totalTokens(),
                request.totalCost());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmExecutionMetricResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmExecutionMetricResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmExecutionMetricId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmExecutionMetricResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmExecutionMetricRequest request) {
        var command = new UpdateLlmExecutionMetricCommand(
                new LlmExecutionMetricId(id),
                new LlmExecutionId(request.executionId()),
                request.inputTokens(),
                request.outputTokens(),
                request.totalTokens(),
                request.totalCost());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmExecutionMetricId(id));
        return ResponseEntity.noContent().build();
    }
}
