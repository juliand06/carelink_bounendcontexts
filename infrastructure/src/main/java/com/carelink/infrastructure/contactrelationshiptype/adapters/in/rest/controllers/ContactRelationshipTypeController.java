package com.carelink.infrastructure.contactrelationshiptype.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.contactrelationshiptype.command.RegisterContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.command.UpdateContactRelationshipTypeCommand;
import com.carelink.application.contactrelationshiptype.dto.ContactRelationshipTypeResponse;
import com.carelink.application.contactrelationshiptype.usecase.DeleteContactRelationshipTypeUseCase;
import com.carelink.application.contactrelationshiptype.usecase.GetContactRelationshipTypeByIdUseCase;
import com.carelink.application.contactrelationshiptype.usecase.ListContactRelationshipTypeUseCase;
import com.carelink.application.contactrelationshiptype.usecase.RegisterContactRelationshipTypeUseCase;
import com.carelink.application.contactrelationshiptype.usecase.UpdateContactRelationshipTypeUseCase;
import com.carelink.domain.contactrelationshiptype.model.valueobject.ContactRelationshipTypeId;
import com.carelink.infrastructure.contactrelationshiptype.adapters.in.rest.dtos.CreateContactRelationshipTypeRequest;
import com.carelink.infrastructure.contactrelationshiptype.adapters.in.rest.dtos.UpdateContactRelationshipTypeRequest;
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
@RequestMapping("/api/contact-relationship-types")
public class ContactRelationshipTypeController {
    private final RegisterContactRelationshipTypeUseCase registerUseCase;
    private final GetContactRelationshipTypeByIdUseCase getByIdUseCase;
    private final ListContactRelationshipTypeUseCase listUseCase;
    private final UpdateContactRelationshipTypeUseCase updateUseCase;
    private final DeleteContactRelationshipTypeUseCase deleteUseCase;

    public ContactRelationshipTypeController(RegisterContactRelationshipTypeUseCase registerUseCase,
            GetContactRelationshipTypeByIdUseCase getByIdUseCase, ListContactRelationshipTypeUseCase listUseCase,
            UpdateContactRelationshipTypeUseCase updateUseCase, DeleteContactRelationshipTypeUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ContactRelationshipTypeResponse> create(@Valid @RequestBody CreateContactRelationshipTypeRequest request) {
        var command = new RegisterContactRelationshipTypeCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ContactRelationshipTypeResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactRelationshipTypeResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ContactRelationshipTypeId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactRelationshipTypeResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateContactRelationshipTypeRequest request) {
        var command = new UpdateContactRelationshipTypeCommand(
                new ContactRelationshipTypeId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ContactRelationshipTypeId(id));
        return ResponseEntity.noContent().build();
    }
}
