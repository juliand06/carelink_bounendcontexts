package com.carelink.infrastructure.informedconsenttype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.informedconsenttype.command.RegisterInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.command.UpdateInformedConsentTypeCommand;
import com.carelink.application.informedconsenttype.dto.InformedConsentTypeResponse;
import com.carelink.application.informedconsenttype.usecase.DeleteInformedConsentTypeUseCase;
import com.carelink.application.informedconsenttype.usecase.GetInformedConsentTypeByIdUseCase;
import com.carelink.application.informedconsenttype.usecase.ListInformedConsentTypeUseCase;
import com.carelink.application.informedconsenttype.usecase.RegisterInformedConsentTypeUseCase;
import com.carelink.application.informedconsenttype.usecase.UpdateInformedConsentTypeUseCase;
import com.carelink.domain.informedconsenttype.model.valueobject.InformedConsentTypeId;
import com.carelink.infrastructure.informedconsenttype.adapters.in.rest.dtos.CreateInformedConsentTypeRequest;
import com.carelink.infrastructure.informedconsenttype.adapters.in.rest.dtos.UpdateInformedConsentTypeRequest;
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
@RequestMapping("/api/informed-consent-types")
public class InformedConsentTypeController {
    private final RegisterInformedConsentTypeUseCase registerUseCase;
    private final GetInformedConsentTypeByIdUseCase getByIdUseCase;
    private final ListInformedConsentTypeUseCase listUseCase;
    private final UpdateInformedConsentTypeUseCase updateUseCase;
    private final DeleteInformedConsentTypeUseCase deleteUseCase;

    public InformedConsentTypeController(RegisterInformedConsentTypeUseCase registerUseCase,
            GetInformedConsentTypeByIdUseCase getByIdUseCase, ListInformedConsentTypeUseCase listUseCase,
            UpdateInformedConsentTypeUseCase updateUseCase, DeleteInformedConsentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<InformedConsentTypeResponse> create(@Valid @RequestBody CreateInformedConsentTypeRequest request) {
        var command = new RegisterInformedConsentTypeCommand(
                request.code(),
                request.label(),
                request.active(),
                request.description());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<InformedConsentTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InformedConsentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new InformedConsentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformedConsentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateInformedConsentTypeRequest request) {
        var command = new UpdateInformedConsentTypeCommand(
                new InformedConsentTypeId(id),
                request.code(),
                request.label(),
                request.active(),
                request.description());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new InformedConsentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
