package com.carelink.infrastructure.llmvendor.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.llmvendor.command.RegisterLlmVendorCommand;
import com.carelink.application.llmvendor.command.UpdateLlmVendorCommand;
import com.carelink.application.llmvendor.dto.LlmVendorResponse;
import com.carelink.application.llmvendor.usecase.DeleteLlmVendorUseCase;
import com.carelink.application.llmvendor.usecase.GetLlmVendorByIdUseCase;
import com.carelink.application.llmvendor.usecase.ListLlmVendorUseCase;
import com.carelink.application.llmvendor.usecase.RegisterLlmVendorUseCase;
import com.carelink.application.llmvendor.usecase.UpdateLlmVendorUseCase;
import com.carelink.domain.llmvendor.model.valueobject.LlmVendorId;
import com.carelink.infrastructure.llmvendor.adapters.in.rest.dtos.CreateLlmVendorRequest;
import com.carelink.infrastructure.llmvendor.adapters.in.rest.dtos.UpdateLlmVendorRequest;
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
@RequestMapping("/api/llm-vendors")
public class LlmVendorController {
    private final RegisterLlmVendorUseCase registerUseCase;
    private final GetLlmVendorByIdUseCase getByIdUseCase;
    private final ListLlmVendorUseCase listUseCase;
    private final UpdateLlmVendorUseCase updateUseCase;
    private final DeleteLlmVendorUseCase deleteUseCase;

    public LlmVendorController(RegisterLlmVendorUseCase registerUseCase,
            GetLlmVendorByIdUseCase getByIdUseCase, ListLlmVendorUseCase listUseCase,
            UpdateLlmVendorUseCase updateUseCase, DeleteLlmVendorUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<LlmVendorResponse> create(@Valid @RequestBody CreateLlmVendorRequest request) {
        var command = new RegisterLlmVendorCommand(
                request.brandName(),
                request.legalName(),
                request.websiteUrl(),
                request.isEnabled());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<LlmVendorResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlmVendorResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new LlmVendorId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LlmVendorResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateLlmVendorRequest request) {
        var command = new UpdateLlmVendorCommand(
                new LlmVendorId(id),
                request.brandName(),
                request.legalName(),
                request.websiteUrl(),
                request.isEnabled());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new LlmVendorId(id));
        return ResponseEntity.noContent().build();
    }
}
