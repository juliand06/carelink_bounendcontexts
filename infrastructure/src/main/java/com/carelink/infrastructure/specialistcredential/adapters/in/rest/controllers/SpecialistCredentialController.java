package com.carelink.infrastructure.specialistcredential.adapters.in.rest.controllers;

import java.util.List;
import java.util.UUID;

import com.carelink.application.specialistcredential.command.RegisterSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.command.UpdateSpecialistCredentialCommand;
import com.carelink.application.specialistcredential.dto.SpecialistCredentialResponse;
import com.carelink.application.specialistcredential.usecase.DeleteSpecialistCredentialUseCase;
import com.carelink.application.specialistcredential.usecase.GetSpecialistCredentialByIdUseCase;
import com.carelink.application.specialistcredential.usecase.ListSpecialistCredentialUseCase;
import com.carelink.application.specialistcredential.usecase.RegisterSpecialistCredentialUseCase;
import com.carelink.application.specialistcredential.usecase.UpdateSpecialistCredentialUseCase;
import com.carelink.domain.academicprogram.model.valueobject.AcademicProgramId;
import com.carelink.domain.country.model.valueobject.CountryId;
import com.carelink.domain.specialist.model.valueobject.SpecialistId;
import com.carelink.domain.specialistcredential.model.valueobject.SpecialistCredentialId;
import com.carelink.infrastructure.specialistcredential.adapters.in.rest.dtos.CreateSpecialistCredentialRequest;
import com.carelink.infrastructure.specialistcredential.adapters.in.rest.dtos.UpdateSpecialistCredentialRequest;
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
@RequestMapping("/api/specialist-credentials")
public class SpecialistCredentialController {
    private final RegisterSpecialistCredentialUseCase registerUseCase;
    private final GetSpecialistCredentialByIdUseCase getByIdUseCase;
    private final ListSpecialistCredentialUseCase listUseCase;
    private final UpdateSpecialistCredentialUseCase updateUseCase;
    private final DeleteSpecialistCredentialUseCase deleteUseCase;

    public SpecialistCredentialController(RegisterSpecialistCredentialUseCase registerUseCase,
            GetSpecialistCredentialByIdUseCase getByIdUseCase, ListSpecialistCredentialUseCase listUseCase,
            UpdateSpecialistCredentialUseCase updateUseCase, DeleteSpecialistCredentialUseCase deleteUseCase) {
        this.registerUseCase = registerUseCase;
        this.getByIdUseCase = getByIdUseCase;
        this.listUseCase = listUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
    }

    @PostMapping
    public ResponseEntity<SpecialistCredentialResponse> create(@Valid @RequestBody CreateSpecialistCredentialRequest request) {
        var command = new RegisterSpecialistCredentialCommand(
                new AcademicProgramId(request.academicProgramId()),
                new SpecialistId(request.specialistId()),
                request.degreeTitle(),
                request.institution(),
                request.isVerified(),
                request.resolutionCode(),
                new CountryId(request.countryId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUseCase.execute(command));
    }

    @GetMapping
    public ResponseEntity<List<SpecialistCredentialResponse>> findAll() {
        return ResponseEntity.ok(listUseCase.execute());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialistCredentialResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(getByIdUseCase.execute(new SpecialistCredentialId(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialistCredentialResponse> update(@PathVariable UUID id,
            @Valid @RequestBody UpdateSpecialistCredentialRequest request) {
        var command = new UpdateSpecialistCredentialCommand(
                new SpecialistCredentialId(id),
                new AcademicProgramId(request.academicProgramId()),
                new SpecialistId(request.specialistId()),
                request.degreeTitle(),
                request.institution(),
                request.isVerified(),
                request.resolutionCode(),
                new CountryId(request.countryId()));
        return ResponseEntity.ok(updateUseCase.execute(command));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteUseCase.execute(new SpecialistCredentialId(id));
        return ResponseEntity.noContent().build();
    }
}
