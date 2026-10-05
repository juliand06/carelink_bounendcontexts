package com.carelink.infrastructure.iddocumenttype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.iddocumenttype.command.RegisterIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.command.UpdateIdDocumentTypeCommand;
import com.carelink.application.iddocumenttype.dto.IdDocumentTypeResponse;
import com.carelink.application.iddocumenttype.usecase.DeleteIdDocumentTypeUseCase;
import com.carelink.application.iddocumenttype.usecase.GetIdDocumentTypeByIdUseCase;
import com.carelink.application.iddocumenttype.usecase.ListIdDocumentTypeUseCase;
import com.carelink.application.iddocumenttype.usecase.RegisterIdDocumentTypeUseCase;
import com.carelink.application.iddocumenttype.usecase.UpdateIdDocumentTypeUseCase;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.infrastructure.iddocumenttype.adapters.in.rest.dtos.CreateIdDocumentTypeRequest;
import com.carelink.infrastructure.iddocumenttype.adapters.in.rest.dtos.UpdateIdDocumentTypeRequest;
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
@RequestMapping("/api/id-document-types")
public class IdDocumentTypeController {
    private final RegisterIdDocumentTypeUseCase registerUseCase;
    private final GetIdDocumentTypeByIdUseCase getByIdUseCase;
    private final ListIdDocumentTypeUseCase listUseCase;
    private final UpdateIdDocumentTypeUseCase updateUseCase;
    private final DeleteIdDocumentTypeUseCase deleteUseCase;

    public IdDocumentTypeController(RegisterIdDocumentTypeUseCase registerUseCase,
            GetIdDocumentTypeByIdUseCase getByIdUseCase, ListIdDocumentTypeUseCase listUseCase,
            UpdateIdDocumentTypeUseCase updateUseCase, DeleteIdDocumentTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<IdDocumentTypeResponse> create(@Valid @RequestBody CreateIdDocumentTypeRequest request) {
        var command = new RegisterIdDocumentTypeCommand(
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<IdDocumentTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdDocumentTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new IdDocumentTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdDocumentTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateIdDocumentTypeRequest request) {
        var command = new UpdateIdDocumentTypeCommand(
                new IdDocumentTypeId(id),
                request.code(),
                request.label(),
                request.active());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new IdDocumentTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
