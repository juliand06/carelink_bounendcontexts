package com.carelink.infrastructure.specialist.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.specialist.command.RegisterSpecialistCommand;
import com.carelink.application.specialist.command.UpdateSpecialistCommand;
import com.carelink.application.specialist.dto.SpecialistResponse;
import com.carelink.application.specialist.usecase.DeleteSpecialistUseCase;
import com.carelink.application.specialist.usecase.GetSpecialistByIdUseCase;
import com.carelink.application.specialist.usecase.ListSpecialistUseCase;
import com.carelink.application.specialist.usecase.RegisterSpecialistUseCase;
import com.carelink.application.specialist.usecase.UpdateSpecialistUseCase;
import com.carelink.domain.iddocumenttype.model.valueobject.IdDocumentTypeId;
import com.carelink.domain.municipality.model.valueobject.MunicipalityId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistrole.model.valueobject.SpecialistRoleId;
import com.carelink.infrastructure.specialist.adapters.in.rest.dtos.CreateSpecialistRequest;
import com.carelink.infrastructure.specialist.adapters.in.rest.dtos.UpdateSpecialistRequest;
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
@RequestMapping("/api/specialists")
public class SpecialistController {
    private final RegisterSpecialistUseCase registerUseCase;
    private final GetSpecialistByIdUseCase getByIdUseCase;
    private final ListSpecialistUseCase listUseCase;
    private final UpdateSpecialistUseCase updateUseCase;
    private final DeleteSpecialistUseCase deleteUseCase;

    public SpecialistController(RegisterSpecialistUseCase registerUseCase,
            GetSpecialistByIdUseCase getByIdUseCase, ListSpecialistUseCase listUseCase,
            UpdateSpecialistUseCase updateUseCase, DeleteSpecialistUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SpecialistResponse> create(@Valid @RequestBody CreateSpecialistRequest request) {
        var command = new RegisterSpecialistCommand(
                new IdDocumentTypeId(request.idDocumentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                new SpecialistRoleId(request.specialistRoleId()),
                request.professionalLicense(),
                request.active(),
                new MunicipalityId(request.municipalityId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SpecialistResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialistResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SpecialistId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialistResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSpecialistRequest request) {
        var command = new UpdateSpecialistCommand(
                new SpecialistId(id),
                new IdDocumentTypeId(request.idDocumentTypeId()),
                request.documentNumber(),
                request.firstName(),
                request.lastName(),
                new SpecialistRoleId(request.specialistRoleId()),
                request.professionalLicense(),
                request.active(),
                new MunicipalityId(request.municipalityId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SpecialistId(id));
        return ResponseEntity.noContent().build();
    }
}
