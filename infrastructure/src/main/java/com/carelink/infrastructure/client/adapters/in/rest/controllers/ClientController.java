package com.carelink.infrastructure.client.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.client.command.RegisterClientCommand;
import com.carelink.application.client.command.UpdateClientCommand;
import com.carelink.application.client.dto.ClientResponse;
import com.carelink.application.client.usecase.DeleteClientUseCase;
import com.carelink.application.client.usecase.GetClientByIdUseCase;
import com.carelink.application.client.usecase.ListClientUseCase;
import com.carelink.application.client.usecase.RegisterClientUseCase;
import com.carelink.application.client.usecase.UpdateClientUseCase;
import com.carelink.domain.biologicalsex.model.valueobject.BiologicalSexId;
import com.carelink.domain.client.model.valueobject.ClientId;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.infrastructure.client.adapters.in.rest.dtos.CreateClientRequest;
import com.carelink.infrastructure.client.adapters.in.rest.dtos.UpdateClientRequest;
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
@RequestMapping("/api/clients")
public class ClientController {
    private final RegisterClientUseCase registerUseCase;
    private final GetClientByIdUseCase getByIdUseCase;
    private final ListClientUseCase listUseCase;
    private final UpdateClientUseCase updateUseCase;
    private final DeleteClientUseCase deleteUseCase;

    public ClientController(RegisterClientUseCase registerUseCase,
            GetClientByIdUseCase getByIdUseCase, ListClientUseCase listUseCase,
            UpdateClientUseCase updateUseCase, DeleteClientUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody CreateClientRequest request) {
        var command = new RegisterClientCommand(
                new IdDocumentTypeId(request.idDocumentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.secondLastName(),
                request.birthDate(),
                new BiologicalSexId(request.biologicalSexId()),
                new BiologicalSexId(request.genderIdentityId()),
                request.email(),
                request.mobilePhone(),
                request.homeAddress(),
                request.active(),
                request.createdBy() == null ? null : new SpecialistId(request.createdBy()),
                request.updatedBy() == null ? null : new SpecialistId(request.updatedBy()),
                new MunicipalityId(request.municipalityId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new ClientId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateClientRequest request) {
        var command = new UpdateClientCommand(
                new ClientId(id),
                new IdDocumentTypeId(request.idDocumentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.middleName(),
                request.lastName(),
                request.secondLastName(),
                request.birthDate(),
                new BiologicalSexId(request.biologicalSexId()),
                new BiologicalSexId(request.genderIdentityId()),
                request.email(),
                request.mobilePhone(),
                request.homeAddress(),
                request.active(),
                request.updatedBy() == null ? null : new SpecialistId(request.updatedBy()),
                new MunicipalityId(request.municipalityId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new ClientId(id));
        return ResponseEntity.noContent().build();
    }
}
