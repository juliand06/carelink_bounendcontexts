package com.carelink.infrastructure.senderrole.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.senderrole.command.RegisterSenderRoleCommand;
import com.carelink.application.senderrole.command.UpdateSenderRoleCommand;
import com.carelink.application.senderrole.dto.SenderRoleResponse;
import com.carelink.application.senderrole.usecase.DeleteSenderRoleUseCase;
import com.carelink.application.senderrole.usecase.GetSenderRoleByIdUseCase;
import com.carelink.application.senderrole.usecase.ListSenderRoleUseCase;
import com.carelink.application.senderrole.usecase.RegisterSenderRoleUseCase;
import com.carelink.application.senderrole.usecase.UpdateSenderRoleUseCase;
import com.carelink.domain.senderrole.model.valueobject.SenderRoleId;
import com.carelink.infrastructure.senderrole.adapters.in.rest.dtos.CreateSenderRoleRequest;
import com.carelink.infrastructure.senderrole.adapters.in.rest.dtos.UpdateSenderRoleRequest;
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
@RequestMapping("/api/sender-roles")
public class SenderRoleController {
    private final RegisterSenderRoleUseCase registerUseCase;
    private final GetSenderRoleByIdUseCase getByIdUseCase;
    private final ListSenderRoleUseCase listUseCase;
    private final UpdateSenderRoleUseCase updateUseCase;
    private final DeleteSenderRoleUseCase deleteUseCase;

    public SenderRoleController(RegisterSenderRoleUseCase registerUseCase,
            GetSenderRoleByIdUseCase getByIdUseCase, ListSenderRoleUseCase listUseCase,
            UpdateSenderRoleUseCase updateUseCase, DeleteSenderRoleUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SenderRoleResponse> create(@Valid @RequestBody CreateSenderRoleRequest request) {
        var command = new RegisterSenderRoleCommand(
                request.label());
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SenderRoleResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SenderRoleResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SenderRoleId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SenderRoleResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSenderRoleRequest request) {
        var command = new UpdateSenderRoleCommand(
                new SenderRoleId(id),
                request.label());
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SenderRoleId(id));
        return ResponseEntity.noContent().build();
    }
}
