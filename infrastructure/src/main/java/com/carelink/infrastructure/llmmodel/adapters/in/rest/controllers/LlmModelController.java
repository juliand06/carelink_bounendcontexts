package com.carelink.infrastructure.llmmodel.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmmodel.command.RegisterLlmModelCommand;
import com.carelink.application.llmmodel.command.UpdateLlmModelCommand;
import com.carelink.application.llmmodel.dto.LlmModelResponse;
import com.carelink.application.llmmodel.usecase.DeleteLlmModelUseCase;
import com.carelink.application.llmmodel.usecase.GetLlmModelByIdUseCase;
import com.carelink.application.llmmodel.usecase.ListLlmModelUseCase;
import com.carelink.application.llmmodel.usecase.RegisterLlmModelUseCase;
import com.carelink.application.llmmodel.usecase.UpdateLlmModelUseCase;
import com.carelink.domain.llmmodel.model.valueobject.LlmModelId;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.infrastructure.llmmodel.adapters.in.rest.dtos.CreateLlmModelRequest;
import com.carelink.infrastructure.llmmodel.adapters.in.rest.dtos.UpdateLlmModelRequest;
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
@RequestMapping("/api/llm-models")
public class LlmModelController {
    private final RegisterLlmModelUseCase registerUseCase;
    private final GetLlmModelByIdUseCase getByIdUseCase;
    private final ListLlmModelUseCase listUseCase;
    private final UpdateLlmModelUseCase updateUseCase;
    private final DeleteLlmModelUseCase deleteUseCase;

    public LlmModelController(RegisterLlmModelUseCase registerUseCase,
            GetLlmModelByIdUseCase getByIdUseCase, ListLlmModelUseCase listUseCase,
            UpdateLlmModelUseCase updateUseCase, DeleteLlmModelUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmModelResponse> create(@Valid @RequestBody CreateLlmModelRequest request) {
        var command = new RegisterLlmModelCommand(
                new LlmVendorId(request.llmVendorId()),
                request.displayName(),
                request.apiIdentifier(),
                request.pricePerInputToken(),
                request.pricePerOutputToken(),
                request.maxOutputTokens(),
                request.contextLength(),
                request.isEnabled());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmModelResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmModelResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmModelId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmModelResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmModelRequest request) {
        var command = new UpdateLlmModelCommand(
                new LlmModelId(id),
                new LlmVendorId(request.llmVendorId()),
                request.displayName(),
                request.apiIdentifier(),
                request.pricePerInputToken(),
                request.pricePerOutputToken(),
                request.maxOutputTokens(),
                request.contextLength(),
                request.isEnabled());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmModelId(id));
        return ResponseEntity.noContent().build();
    }
}
