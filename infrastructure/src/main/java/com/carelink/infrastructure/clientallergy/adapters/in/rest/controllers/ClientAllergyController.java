package com.carelink.infrastructure.clientallergy.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.clientallergy.command.RegisterClientAllergyCommand;
import com.carelink.application.clientallergy.command.UpdateClientAllergyCommand;
import com.carelink.application.clientallergy.dto.ClientAllergyResponse;
import com.carelink.application.clientallergy.usecase.DeleteClientAllergyUseCase;
import com.carelink.application.clientallergy.usecase.GetClientAllergyByIdUseCase;
import com.carelink.application.clientallergy.usecase.ListClientAllergyUseCase;
import com.carelink.application.clientallergy.usecase.RegisterClientAllergyUseCase;
import com.carelink.application.clientallergy.usecase.UpdateClientAllergyUseCase;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.clientallergy.model.valueobject.ClientAllergyId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.clientallergy.adapters.in.rest.dtos.CreateClientAllergyRequest;
import com.carelink.infrastructure.clientallergy.adapters.in.rest.dtos.UpdateClientAllergyRequest;
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
@RequestMapping("/api/client-allergies")
public class ClientAllergyController {
    private final RegisterClientAllergyUseCase registerUseCase;
    private final GetClientAllergyByIdUseCase getByIdUseCase;
    private final ListClientAllergyUseCase listUseCase;
    private final UpdateClientAllergyUseCase updateUseCase;
    private final DeleteClientAllergyUseCase deleteUseCase;

    public ClientAllergyController(RegisterClientAllergyUseCase registerUseCase,
            GetClientAllergyByIdUseCase getByIdUseCase, ListClientAllergyUseCase listUseCase,
            UpdateClientAllergyUseCase updateUseCase, DeleteClientAllergyUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientAllergyResponse> create(@Valid @RequestBody CreateClientAllergyRequest request) {
        var command = new RegisterClientAllergyCommand(
                new ClientId(request.clientId()),
                request.allergen(),
                request.reactionDescription(),
                request.severityLevel(),
                request.active(),
                new SpecialistId(request.registeredBy()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClientAllergyResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientAllergyResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClientAllergyId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientAllergyResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateClientAllergyRequest request) {
        var command = new UpdateClientAllergyCommand(
                new ClientAllergyId(id),
                new ClientId(request.clientId()),
                request.allergen(),
                request.reactionDescription(),
                request.severityLevel(),
                request.active(),
                new SpecialistId(request.registeredBy()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ClientAllergyId(id));
        return ResponseEntity.noContent().build();
    }
}
