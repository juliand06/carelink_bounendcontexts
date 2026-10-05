package com.carelink.infrastructure.trustedcontact.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.trustedcontact.command.RegisterTrustedContactCommand;
import com.carelink.application.trustedcontact.command.UpdateTrustedContactCommand;
import com.carelink.application.trustedcontact.dto.TrustedContactResponse;
import com.carelink.application.trustedcontact.usecase.DeleteTrustedContactUseCase;
import com.carelink.application.trustedcontact.usecase.GetTrustedContactByIdUseCase;
import com.carelink.application.trustedcontact.usecase.ListTrustedContactUseCase;
import com.carelink.application.trustedcontact.usecase.RegisterTrustedContactUseCase;
import com.carelink.application.trustedcontact.usecase.UpdateTrustedContactUseCase;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.trustedcontact.adapters.in.rest.dtos.CreateTrustedContactRequest;
import com.carelink.infrastructure.trustedcontact.adapters.in.rest.dtos.UpdateTrustedContactRequest;
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
@RequestMapping("/api/trusted-contacts")
public class TrustedContactController {
    private final RegisterTrustedContactUseCase registerUseCase;
    private final GetTrustedContactByIdUseCase getByIdUseCase;
    private final ListTrustedContactUseCase listUseCase;
    private final UpdateTrustedContactUseCase updateUseCase;
    private final DeleteTrustedContactUseCase deleteUseCase;

    public TrustedContactController(RegisterTrustedContactUseCase registerUseCase,
            GetTrustedContactByIdUseCase getByIdUseCase, ListTrustedContactUseCase listUseCase,
            UpdateTrustedContactUseCase updateUseCase, DeleteTrustedContactUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<TrustedContactResponse> create(@Valid @RequestBody CreateTrustedContactRequest request) {
        var command = new RegisterTrustedContactCommand(
                request.fullName(),
                new ContactRelationshipTypeId(request.relationshipTypeId()),
                request.isPrimaryContact(),
                request.notes());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<TrustedContactResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrustedContactResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new TrustedContactId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrustedContactResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateTrustedContactRequest request) {
        var command = new UpdateTrustedContactCommand(
                new TrustedContactId(id),
                request.fullName(),
                new ContactRelationshipTypeId(request.relationshipTypeId()),
                request.isPrimaryContact(),
                request.notes());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new TrustedContactId(id));
        return ResponseEntity.noContent().build();
    }
}
