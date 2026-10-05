package com.carelink.infrastructure.clienttrustedcontact.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.clienttrustedcontact.command.RegisterClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.command.UpdateClientTrustedContactCommand;
import com.carelink.application.clienttrustedcontact.dto.ClientTrustedContactResponse;
import com.carelink.application.clienttrustedcontact.usecase.DeleteClientTrustedContactUseCase;
import com.carelink.application.clienttrustedcontact.usecase.GetClientTrustedContactByIdUseCase;
import com.carelink.application.clienttrustedcontact.usecase.ListClientTrustedContactUseCase;
import com.carelink.application.clienttrustedcontact.usecase.RegisterClientTrustedContactUseCase;
import com.carelink.application.clienttrustedcontact.usecase.UpdateClientTrustedContactUseCase;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clienttrustedcontact.model.valueobject.ClientTrustedContactId;
import com.carelink.domain.trustedcontact.model.valueobject.TrustedContactId;
import com.carelink.infrastructure.clienttrustedcontact.adapters.in.rest.dtos.CreateClientTrustedContactRequest;
import com.carelink.infrastructure.clienttrustedcontact.adapters.in.rest.dtos.UpdateClientTrustedContactRequest;
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
@RequestMapping("/api/client-trusted-contacts")
public class ClientTrustedContactController {
    private final RegisterClientTrustedContactUseCase registerUseCase;
    private final GetClientTrustedContactByIdUseCase getByIdUseCase;
    private final ListClientTrustedContactUseCase listUseCase;
    private final UpdateClientTrustedContactUseCase updateUseCase;
    private final DeleteClientTrustedContactUseCase deleteUseCase;

    public ClientTrustedContactController(RegisterClientTrustedContactUseCase registerUseCase,
            GetClientTrustedContactByIdUseCase getByIdUseCase, ListClientTrustedContactUseCase listUseCase,
            UpdateClientTrustedContactUseCase updateUseCase, DeleteClientTrustedContactUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientTrustedContactResponse> create(@Valid @RequestBody CreateClientTrustedContactRequest request) {
        var command = new RegisterClientTrustedContactCommand(
                new ClientId(request.clientId()),
                new TrustedContactId(request.trustedContactId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClientTrustedContactResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientTrustedContactResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClientTrustedContactId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientTrustedContactResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateClientTrustedContactRequest request) {
        var command = new UpdateClientTrustedContactCommand(
                new ClientTrustedContactId(id),
                new ClientId(request.clientId()),
                new TrustedContactId(request.trustedContactId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ClientTrustedContactId(id));
        return ResponseEntity.noContent().build();
    }
}
